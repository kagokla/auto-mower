package com.github.kagokla.automower.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mower's cardinal orientation on the lawn.
 */
@Getter
public enum Orientation {
    NORTH('N'),
    EAST('E'),
    WEST('W'),
    SOUTH('S');

    private static final Map<Character, Orientation> labelLookup =
            Arrays.stream(values()).collect(Collectors.toUnmodifiableMap(Orientation::getLabel, Function.identity()));
    private final char label;

    Orientation(char label) {
        this.label = label;
    }

    public static Orientation fromLabel(final char label) {
        return labelLookup.get(Character.toUpperCase(label));
    }

    public Orientation toLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
        };
    }

    public Orientation toRight() {
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
        return String.valueOf(label);
    }
}
