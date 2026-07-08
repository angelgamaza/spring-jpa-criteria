package com.gamaza.examples.springjpacriteria.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.gamaza.examples.springjpacriteria.constant.GenericConstants.HYPHEN;
import static com.gamaza.examples.springjpacriteria.constant.GenericConstants.PLUS;

/**
 * Generic utils class
 */
public final class Utils {

    /**
     * Build the pagination data for the given page, size and orders
     *
     * @param page  The page to retrieve
     * @param size  The elements to retrieve
     * @param order The order to apply
     * @return The build {@link Pageable}
     */
    public static Pageable buildPaginationData(Integer page, Integer size, List<String> order) {
        if (CollectionUtils.isEmpty(order)) {
            return PageRequest.of(page, size);
        }
        return PageRequest.of(page, size, Utils.buildOrderData(order));
    }

    /**
     * Build the {@link Sort} for the given Orders {@link List} by {@link String}
     *
     * @param order The orders {@link List}
     * @return The built {@link Sort}
     */
    public static Sort buildOrderData(List<String> order) {
        List<Sort.Order> result = order
                .stream()
                .map(current -> {
                    // Check order direction
                    boolean descending = current.startsWith(HYPHEN);
                    boolean ascending = current.startsWith(PLUS);
                    // Remove the direction prefix if exists
                    String rawField = (descending || ascending) ? current.substring(1) : current;
                    return descending ? Sort.Order.desc(rawField) : Sort.Order.asc(rawField);
                })
                .toList();

        return Sort.by(result);
    }

    /**
     * Private constructor to prevent instantiation
     */
    private Utils() {
        throw new IllegalStateException("Could not instantiate Utils class!");
    }

}