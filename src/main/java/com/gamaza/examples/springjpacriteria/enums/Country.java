package com.gamaza.examples.springjpacriteria.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.gamaza.examples.springjpacriteria.enums.base.BaseEnum;

import java.util.Arrays;
import java.util.List;

/**
 * Enum for Countries
 */
public enum Country implements BaseEnum {

    // Enum values
    ITALY("Italy"),
    SPAIN("Spain"),
    AUSTRALIA("Australia"),
    AUSTRIA("Austria"),
    JAPAN("Japan"),
    USA("United States"),
    SOUTH_AFRICA("South Africa"),
    THAILAND("Thailand"),
    PORTUGAL("Portugal"),
    MONACO("Monaco"),
    FRANCE("France");

    // Enum attributes
    private final String value;

    /**
     * Constructor
     *
     * @param value The enum value
     */
    Country(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static Country fromString(String value) {
        for (Country currentInstance : Country.values())
            if (currentInstance.value.equalsIgnoreCase(value)) {
                return currentInstance;
            }
        throw new IllegalArgumentException("No constant with value: " + value + " found");
    }

    public static List<Country> getValues() {
        return Arrays.asList(Country.values());
    }

}
