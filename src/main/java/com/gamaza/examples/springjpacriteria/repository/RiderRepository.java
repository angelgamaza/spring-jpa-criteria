package com.gamaza.examples.springjpacriteria.repository;

import com.gamaza.examples.springjpacriteria.dto.request.RiderFilterDto;
import com.gamaza.examples.springjpacriteria.entity.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Repository for Rider operations
 */
public interface RiderRepository {

    /**
     * Find {@link Rider} data by custom parameters
     *
     * @param filter   The {@link RiderFilterDto} to apply
     * @param pageable The {@link Pageable} data
     * @return The found data as {@link Page<Rider>}
     */
    Page<Rider> findByCustomParams(RiderFilterDto filter, Pageable pageable);

}
