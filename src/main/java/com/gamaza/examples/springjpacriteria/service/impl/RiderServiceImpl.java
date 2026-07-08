package com.gamaza.examples.springjpacriteria.service.impl;

import com.gamaza.examples.springjpacriteria.dto.request.RiderFilterDto;
import com.gamaza.examples.springjpacriteria.dto.response.rider.RiderRelationsDto;
import com.gamaza.examples.springjpacriteria.mapper.RiderMapper;
import com.gamaza.examples.springjpacriteria.repository.RiderRepository;
import com.gamaza.examples.springjpacriteria.service.RiderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RiderServiceImpl implements RiderService {

    // Injected variables
    private final RiderRepository repository;
    private final RiderMapper mapper;

    public RiderServiceImpl(RiderRepository repository, RiderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<RiderRelationsDto> findByCustomParams(RiderFilterDto filter, Pageable pageable) {
        return repository
                .findByCustomParams(filter, pageable)
                .map(mapper::asRelationsDto);
    }

}
