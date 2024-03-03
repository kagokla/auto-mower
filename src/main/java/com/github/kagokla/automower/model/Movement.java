package com.github.kagokla.automower.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * Mower's movement. The mower can move forward or rotate according to the movement.
 */
public enum Movement {
    FORWARD("F"),
    LEFT("L"),
    RIGHT("R");

    private final String label;

    Movement(final String label) {
        this.label = label;
    }

    public static Movement fromValue(String label) {
        return Arrays.stream(values())
                .filter(movement -> movement.label.equalsIgnoreCase(label))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    @Override
    public String toString() {
        return label;
    }
}
