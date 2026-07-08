package com.gamaza.examples.springjpacriteria.mapper;

import com.gamaza.examples.springjpacriteria.dto.response.rider.RiderRelationsDto;
import com.gamaza.examples.springjpacriteria.entity.Rider;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * Mapper for Rider
 */
@Mapper(componentModel = SPRING, unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE, typeConversionPolicy = IGNORE)
public interface RiderMapper {

    RiderRelationsDto asRelationsDto(Rider source);

}
