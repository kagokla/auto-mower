package com.github.kagokla.automower.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * Mower's cardinal orientation on the lawn.
 */
public enum Orientation {
    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private final String label;

    Orientation(String label) {
        this.label = label;
    }

    public static Orientation fromValue(String label) {
        return Arrays.stream(values())
                .filter(orientation -> orientation.label.equalsIgnoreCase(label))
                .findFirst()
                .orElse(null);
    }

    public Orientation rotateLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
        };
    }

    public Orientation rotateRight() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
            case SOUTH -> WEST;
        };
    }

    @JsonValue
    @Override
    public String toString() {
        return label;
    }
}
