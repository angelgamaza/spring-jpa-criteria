package com.gamaza.examples.springjpacriteria.repository.impl;

import com.gamaza.examples.springjpacriteria.dto.request.RiderFilterDto;
import com.gamaza.examples.springjpacriteria.entity.Rider;
import com.gamaza.examples.springjpacriteria.repository.RiderRepository;
import com.gamaza.examples.springjpacriteria.repository.template.CriteriaRepositoryTemplate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

import static com.gamaza.examples.springjpacriteria.constant.EntityConstants.*;

@Repository
public class RiderRepositoryImpl extends CriteriaRepositoryTemplate<Rider, RiderFilterDto, Long> implements RiderRepository {

    public RiderRepositoryImpl() {
        super(FIELD_ID_STRING, RIDER_ENTITY_GRAPH);
    }

    @Override
    public Page<Rider> findByCustomParams(RiderFilterDto filter, Pageable pageable) {
        return this.executeCustomSearch(filter, pageable);
    }

    @Override
    public Predicate[] buildQueryPredicates(CriteriaBuilder criteriaBuilder, Root<Rider> rootEntity, RiderFilterDto filter) {
        // Null filter received
        List<Predicate> predicates = new LinkedList<>();
        if (filter == null) {
            return new Predicate[0];
        }
        // Rider name
        Predicate result = this.buildPredicateIfHasValue(criteriaBuilder, rootEntity.get(FIELD_NAME_STRING), filter.riderName());
        if (result != null) {
            predicates.add(result);
        }
        // Rider country
        result = this.buildPredicateIfHasValue(criteriaBuilder, rootEntity.get(FIELD_COUNTRY_STRING), filter.riderCountry());
        if (result != null) {
            predicates.add(result);
        }
        // Rider number
        result = this.buildPredicateIfHasValue(criteriaBuilder, rootEntity.get(FIELD_NUMBER_STRING), filter.riderNumber());
        if (result != null) {
            predicates.add(result);
        }
        // Rider championships
        result = this.buildPredicateIfHasValue(criteriaBuilder, rootEntity.get(FIELD_CHAMPIONSHIPS_STRING), filter.riderChampionships());
        if (result != null) {
            predicates.add(result);
        }
        // Team name
        result = this.buildPredicateIfHasValue(criteriaBuilder, rootEntity.get(FIELD_TEAM_STRING).get(FIELD_NAME_STRING), filter.teamName());
        if (result != null) {
            predicates.add(result);
        }
        // Team constructor
        result = this.buildPredicateIfHasValue(criteriaBuilder, rootEntity.get(FIELD_TEAM_STRING).get(FIELD_CONSTRUCTOR_STRING), filter.teamConstructor());
        if (result != null) {
            predicates.add(result);
        }
        // Team motorcycle
        result = this.buildPredicateIfHasValue(criteriaBuilder, rootEntity.get(FIELD_TEAM_STRING).get(FIELD_MOTORCYCLE_STRING), filter.teamMotorcycle());
        if (result != null) {
            predicates.add(result);
        }
        // Team country
        result = this.buildPredicateIfHasValue(criteriaBuilder, rootEntity.get(FIELD_TEAM_STRING).get(FIELD_COUNTRY_STRING), filter.teamCountry());
        if (result != null) {
            predicates.add(result);
        }

        return predicates.toArray(new Predicate[0]);
    }

    @Override
    protected Path<Rider> buildRelationshipsPathExpression(CriteriaBuilder criteriaBuilder, Root<Rider> rootEntity, String property) {
        return null;
    }

}
