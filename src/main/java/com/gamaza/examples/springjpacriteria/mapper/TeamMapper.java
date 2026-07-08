package com.gamaza.examples.springjpacriteria.mapper;

import com.gamaza.examples.springjpacriteria.dto.response.team.TeamDto;
import com.gamaza.examples.springjpacriteria.entity.Team;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * Mapper for Team
 */
@Mapper(componentModel = SPRING, unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE, typeConversionPolicy = IGNORE)
public interface TeamMapper {

    TeamDto asDto(Team source);

}
