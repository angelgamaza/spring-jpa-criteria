package com.gamaza.examples.springjpacriteria.dto.response.rider;

import com.gamaza.examples.springjpacriteria.dto.response.team.TeamDto;
import com.gamaza.examples.springjpacriteria.enums.Country;

/**
 * Record for Rider with Relations
 */
public record RiderRelationsDto(Long id, String name, Country country, Integer number, Integer championships, TeamDto team) {
}
