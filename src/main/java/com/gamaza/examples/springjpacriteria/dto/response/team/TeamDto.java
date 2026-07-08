package com.gamaza.examples.springjpacriteria.dto.response.team;

import com.gamaza.examples.springjpacriteria.enums.Constructor;
import com.gamaza.examples.springjpacriteria.enums.Country;

import java.io.Serializable;

/**
 * Record for Team
 */
public record TeamDto(Long id, String name, Constructor constructor, String motorcycle, Country country) implements Serializable {
}
