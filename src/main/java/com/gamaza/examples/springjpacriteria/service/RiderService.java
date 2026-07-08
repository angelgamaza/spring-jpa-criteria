package com.gamaza.examples.springjpacriteria.service;

import com.gamaza.examples.springjpacriteria.dto.request.RiderFilterDto;
import com.gamaza.examples.springjpacriteria.dto.response.rider.RiderRelationsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for Rider
 */
public interface RiderService {

    /**
     * Find {@link RiderRelationsDto} data by custom parameters
     *
     * @param filter   The {@link RiderFilterDto} to apply
     * @param pageable The {@link Page} to apply
     * @return The found data as a {@link Page<RiderRelationsDto>}
     */
    Page<RiderRelationsDto> findByCustomParams(RiderFilterDto filter, Pageable pageable);

}
