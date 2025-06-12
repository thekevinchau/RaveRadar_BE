package com.project.RaveRadar.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AgeRestriction {
    ALL_AGES("All Ages"),
    EIGHTEEN_PLUS("18+"),
    TWENTY_ONE_PLUS("21+");

    private final String label;

    AgeRestriction(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

    @JsonCreator
    public static AgeRestriction fromLabel(String input) {
        for (AgeRestriction value : AgeRestriction.values()) {
            if (value.label.equalsIgnoreCase(input.trim())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid age restriction: " + input);
    }
}