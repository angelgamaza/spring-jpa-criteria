package com.gamaza.examples.springjpacriteria.dto.request;

import com.gamaza.examples.springjpacriteria.enums.Constructor;
import com.gamaza.examples.springjpacriteria.enums.Country;

/**
 * Filter data for Rider custom search
 */
public record RiderFilterDto(
        String riderName,
        Country riderCountry,
        Integer riderNumber,
        Integer riderChampionships,
        String teamName,
        Constructor teamConstructor,
        String teamMotorcycle,
        Country teamCountry
) {
}
