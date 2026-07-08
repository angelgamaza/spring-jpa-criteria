package com.gamaza.examples.springjpacriteria.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.gamaza.examples.springjpacriteria.enums.base.BaseEnum;

import java.util.Arrays;
import java.util.List;

/**
 * Enum for Constructors
 */
public enum Constructor implements BaseEnum {

    // Enum values
    APRILIA("Aprilia"),
    DUCATI("Ducati"),
    HONDA("Honda"),
    YAMAHA("Yamaha"),
    KTM("KTM");

    // Enum attributes
    private final String value;

    /**
     * Constructor
     *
     * @param value The enum value
     */
    Constructor(String value) {
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

    public static Constructor fromString(String value) {
        for (Constructor currentInstance : Constructor.values())
            if (currentInstance.value.equalsIgnoreCase(value)) {
                return currentInstance;
            }
        throw new IllegalArgumentException("No constant with value: " + value + " found");
    }

    public static List<Constructor> getValues() {
        return Arrays.asList(Constructor.values());
    }

}
