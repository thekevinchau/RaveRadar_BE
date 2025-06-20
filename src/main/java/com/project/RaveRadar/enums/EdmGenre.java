package com.project.RaveRadar.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

//The enum is also associated with a label that we can reference in the API when listening them all out.
public enum EdmGenre {
    HOUSE("House"),
    TECHNO("Techno"),
    TRANCE("Trance"),
    DUBSTEP("Dubstep"),
    DRUM_AND_BASS("Drum and Bass"),
    BASS("BASS"),
    FUTURE_BASS("Future Bass"),
    PROGRESSIVE_HOUSE("Progressive House"),
    ELECTRO_HOUSE("Electro House"),
    DEEP_HOUSE("Deep House"),
    TRAP("Trap"),
    HARDSTYLE("Hardstyle"),
    TROPICAL_HOUSE("Tropical House"),
    CHILLOUT("Chillout"),
    BIG_ROOM("Big Room"),
    TECH_HOUSE("Tech House"),
    PSYTRANCE("Psytrance"),
    GLITCH_HOP("Glitch Hop"),
    ACID_HOUSE("Acid House"),
    BASS_HOUSE("Bass House"),
    MELODIC_BASS("Melodic Bass"),
    MELODIC_TECHNO("Melodic Techno"),
    MIDTEMPO("Midtempo"),
    VARIOUS("Various"),
    AMBIENT("Ambient");


    private final String label;

    EdmGenre(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }


    @JsonCreator
    public static EdmGenre fromLabel(String input) {
        for (EdmGenre value : EdmGenre.values()) {
            if (value.label.equalsIgnoreCase(input.trim())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Genre Type: " + input);
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}

