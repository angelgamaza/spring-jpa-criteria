package com.gamaza.examples.springjpacriteria.controller;

import com.gamaza.examples.springjpacriteria.dto.request.RiderFilterDto;
import com.gamaza.examples.springjpacriteria.dto.response.rider.RiderRelationsDto;
import com.gamaza.examples.springjpacriteria.service.RiderService;
import com.gamaza.examples.springjpacriteria.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/rider")
public class RiderController {

    // Injected variables
    private final RiderService service;

    public RiderController(RiderService service) {
        this.service = service;
    }

    @PostMapping
    public Page<RiderRelationsDto> findByCustomParams(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) List<String> order,
            @RequestBody RiderFilterDto filter
    ) {
        var pageable = Utils.buildPaginationData(page, size, order);
        return service.findByCustomParams(filter, pageable);
    }

}
