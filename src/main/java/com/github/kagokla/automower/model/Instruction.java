package com.github.kagokla.automower.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * Mower's instruction. The mower can move forward or rotate according to an instruction.
 */
public enum Instruction {
    FORWARD("F"),
    LEFT("L"),
    RIGHT("R");

    private final String label;

    Instruction(final String label) {
        this.label = label;
    }

    public static Instruction fromValue(String label) {
        return Arrays.stream(values())
                .filter(instruction -> instruction.label.equalsIgnoreCase(label))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    @Override
    public String toString() {
        return label;
    }
}
