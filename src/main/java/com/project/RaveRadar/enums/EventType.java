package com.project.RaveRadar.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EventType {
    SOLO_PERFORMANCE("Solo Performance"),
    BACK_TO_BACK("Back to Back (B2B)"),
    FESTIVAL("Festival"),
    CLUB("Club"),
    LIVE_PERFORMANCE("Live Act"),
    SHOWCASE("Showcase"),
    RAVE("Rave"),
    EXTENDED_SET("Extended Set"),
    WARM_UP("Warm-up Set"),
    AFTERPARTY("Afterparty");

    private final String label;

    EventType(String label) {
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
    public static EventType fromLabel(String input) {
        for (EventType value : EventType.values()) {
            if (value.label.equalsIgnoreCase(input.trim())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Event Type: " + input);
    }
}