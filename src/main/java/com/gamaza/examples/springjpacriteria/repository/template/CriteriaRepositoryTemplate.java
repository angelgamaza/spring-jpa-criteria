package com.gamaza.examples.springjpacriteria.repository.template;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.NoRepositoryBean;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD;

/**
 * Template for Repositories implementation with criteria
 *
 * @author amgamaza
 */
@NoRepositoryBean
@SuppressWarnings("unchecked")
public abstract class CriteriaRepositoryTemplate<E, F, I> {

    // Class formatters
    protected static final String LIKE_FORMATTER = "%%%s%%";

    // ID Field name and entity graph (optional) for queries
    private final String idFieldName;
    private final String entityGraph;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Constructor
     *
     * @param idFieldName The name of the ID field in the entity
     */
    protected CriteriaRepositoryTemplate(String idFieldName) {
        super();
        this.idFieldName = idFieldName;
        this.entityGraph = null;
    }

    /**
     * Constructor
     *
     * @param idFieldName The name of the ID field in the entity
     * @param entityGraph The {@link EntityGraph} to apply for the queries
     */
    protected CriteriaRepositoryTemplate(String idFieldName, String entityGraph) {
        super();
        this.idFieldName = idFieldName;
        this.entityGraph = entityGraph;
    }

    /**
     * Custom base search for Custom Repositories
     *
     * @param filter   The filter to apply
     * @param pageable The {@link Pageable} data
     * @return The found data as a {@link Page} of entities
     */
    protected Page<E> executeCustomSearch(F filter, Pageable pageable) {
        // Get the Criteria builder from Entity Manager
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        // Get the generic types via reflection
        Class<E> entityClass = this.getGenericClass(getClass(), 0);

        /*
         * 1. Build a query to retrieve distinct IDs matching the filter
         */
        CriteriaQuery<Tuple> idsQuery = criteriaBuilder.createTupleQuery().distinct(true);
        Root<E> rootEntity = idsQuery.from(entityClass);

        // Build predicates and ids query
        Predicate[] predicates = this.buildQueryPredicates(criteriaBuilder, rootEntity, filter);
        var sortData = pageable.getSort();
        idsQuery
                .multiselect(
                        this.buildQuerySelection(criteriaBuilder, rootEntity, sortData)
                )
                .where(
                        criteriaBuilder.and(predicates)
                )
                .orderBy(
                        this.buildQueryOrder(criteriaBuilder, rootEntity, sortData)
                );

        // Perform the IDs query
        List<I> ids = entityManager
                .createQuery(idsQuery)
                .setHint("org.hibernate.readOnly", TRUE)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList()
                .stream()
                .map(tuple -> (I) tuple.get(idFieldName))
                .toList();

        // Return an empty page if ids has no results
        if (ids.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        /*
         * 2. Build a count query to determine total number of matching entities
         */
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class).distinct(true);
        rootEntity = countQuery.from(entityClass);
        predicates = this.buildQueryPredicates(criteriaBuilder, rootEntity, filter);
        countQuery
                .select(
                        criteriaBuilder.count(rootEntity)
                )
                .where(
                        criteriaBuilder.and(predicates)
                );

        // Perform the Count query
        Long count = entityManager
                .createQuery(countQuery)
                .getSingleResult();

        /*
         * 3. Build the main query to fetch the actual entities by their IDs
         */
        CriteriaQuery<E> auditQuery = criteriaBuilder.createQuery(entityClass);
        rootEntity = auditQuery.from(entityClass);
        predicates = this.buildQueryPredicates(criteriaBuilder, rootEntity, filter);
        auditQuery
                .where(
                        criteriaBuilder.and(predicates),
                        criteriaBuilder.and(rootEntity.get(idFieldName).in(ids))
                )
                .orderBy(
                        this.buildQueryOrder(criteriaBuilder, rootEntity, sortData)
                );

        // Perform the Main query
        TypedQuery<E> audit = entityManager
                .createQuery(auditQuery)
                .setHint("org.hibernate.readOnly", TRUE);

        // Apply an EntityGraph if provided
        if (entityGraph != null) {
            audit.setHint(LOAD.getKey(), entityManager.getEntityGraph(entityGraph));
        }

        return new PageImpl<>(audit.getResultList(), pageable, count);
    }

    /**
     * Builds a {@link Predicate} array for the Custom Search
     *
     * @param criteriaBuilder The {@link CriteriaBuilder} to be used
     * @param rootEntity      The {@link Root} for the search
     * @param filter          The filter to apply
     * @return The built {@link Predicate} array
     */
    protected abstract Predicate[] buildQueryPredicates(CriteriaBuilder criteriaBuilder, Root<E> rootEntity, F filter);

    /**
     * Builds the {@link Path} expresion for the relationship columns
     *
     * @param criteriaBuilder The {@link CriteriaBuilder} to be used
     * @param rootEntity      The {@link Root} for the search
     * @param property        The {@link Order} property to check
     * @return The built {@link Path} expression
     */
    protected abstract Path<E> buildRelationshipsPathExpression(CriteriaBuilder criteriaBuilder, Root<E> rootEntity, String property);

    /**
     * Builds a {@link Predicate} based on the provided value
     *
     * @param <T>             The value type
     * @param criteriaBuilder The {@link CriteriaBuilder} to be used
     * @param path            The {@link Path} to the field in the entity
     * @param value           The value to be used in the predicate
     * @return A {@link Predicate} if the value is not null and matches one of the expected types, otherwise null
     */
    protected <T> Predicate buildPredicateIfHasValue(CriteriaBuilder criteriaBuilder, Path<String> path, T value) {
        // Null values
        if (value == null) {
            return null;
        }
        // String parameters
        if (value instanceof String stringValue) {
            var formattedValue = String.format(LIKE_FORMATTER, stringValue.toUpperCase());
            return criteriaBuilder.like(
                    criteriaBuilder.upper(path), formattedValue
            );
        }
        // Integer/Long/Enum parameters
        if (value instanceof Integer || value instanceof Long || value instanceof Enum || value instanceof Boolean) {
            return criteriaBuilder.equal(path, value);
        }
        // Invalid classes
        throw new IllegalStateException("Unexpected value: " + value.getClass());
    }

    /**
     * Retrieves the generic {@link Class} type from the superclass at the specified index
     *
     * @param clazz The {@link Class} to extract the generic type from
     * @param index The index of the generic type
     * @param <T>   The {@link Class} type
     * @return The extracted {@link Class} type
     */
    private <T> Class<T> getGenericClass(Class<?> clazz, int index) {
        return (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[index];
    }

    /**
     * Build the {@link Selection} for queries based on {@link Sort} parameters
     *
     * @param criteriaBuilder The {@link CriteriaBuilder} to be used
     * @param rootEntity      The {@link Root} for the search
     * @param sort            The {@link Sort} data
     * @return The built {@link Selection} list
     */
    private List<Selection<?>> buildQuerySelection(CriteriaBuilder criteriaBuilder, Root<E> rootEntity, Sort sort) {
        return Stream.concat(
                Stream.of(rootEntity.get(idFieldName).alias(idFieldName)),
                sort.stream()
                        .filter(current -> !current.getProperty().equals(idFieldName))
                        .map(current -> {
                            Expression<?> expression = this.buildPathExpression(criteriaBuilder, rootEntity, current.getProperty());
                            if (expression.getJavaType() == String.class) {
                                return criteriaBuilder.lower((Expression<String>) expression).alias(current.getProperty());
                            }
                            return expression.alias(current.getProperty());
                        })
        ).toList();
    }

    /**
     * Builds the {@link Order} for the queries
     *
     * @param criteriaBuilder The {@link CriteriaBuilder} to be used
     * @param rootEntity      The {@link Root} for the search
     * @param sort            The {@link Sort} data
     * @return The built {@link Order} list
     */
    private List<Order> buildQueryOrder(CriteriaBuilder criteriaBuilder, Root<E> rootEntity, Sort sort) {
        // Map the incoming Sort object to Criteria API Orders
        Stream<Order> primaryOrders = sort
                .stream()
                .map(order -> this.buildQueryOrderForProperty(criteriaBuilder, rootEntity, order));

        // Determine if we need the fallback ID sort using getOrderFor()
        Stream<Order> fallbackOrder = sort.getOrderFor(idFieldName) == null
                ? Stream.of(criteriaBuilder.desc(rootEntity.get(idFieldName)))
                : Stream.empty();

        // Concatenate and return an unmodifiable list
        return Stream.concat(primaryOrders, fallbackOrder).toList();
    }

    /**
     * Builds a single {@link Order} for a given property {@link Path} and {@link Direction}
     *
     * @param criteriaBuilder The {@link CriteriaBuilder} to be used
     * @param rootEntity      The {@link Root} for the search
     * @param order           The {@link Order} to apply
     * @return The {@link Order} built
     */
    private Order buildQueryOrderForProperty(CriteriaBuilder criteriaBuilder, Root<E> rootEntity, Sort.Order order) {
        Expression<?> expression = this.buildPathExpression(criteriaBuilder, rootEntity, order.getProperty());
        if (expression.getJavaType() == String.class) {
            Expression<String> lowerPath = criteriaBuilder.lower((Expression<String>) expression);
            return order.isAscending() ? criteriaBuilder.asc(lowerPath) : criteriaBuilder.desc(lowerPath);
        }
        return order.isAscending() ? criteriaBuilder.asc(expression) : criteriaBuilder.desc(expression);
    }

    /**
     * Build the {@link Path} expression for the entity attributes
     *
     * @param criteriaBuilder The {@link CriteriaBuilder} to be used
     * @param rootEntity      The {@link Root} for the search
     * @param property        The {@link Order} property to check
     * @return The built {@link Path} expression
     */
    private Expression<?> buildPathExpression(CriteriaBuilder criteriaBuilder, Root<E> rootEntity, String property) {
        // Handle relationship columns sorting
        Path<?> relationshipsPathExpression = this.buildRelationshipsPathExpression(criteriaBuilder, rootEntity, property);
        if (relationshipsPathExpression != null) {
            return relationshipsPathExpression;
        }
        // Standard path get for singular fields
        Path<?> path = rootEntity;
        for (String part : property.split("\\.")) {
            path = path.get(part);
        }
        return path;
    }

}
