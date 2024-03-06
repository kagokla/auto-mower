package com.github.kagokla.automower.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * Mower's instruction. The mower can move forward or rotate according to an instruction.
 */
public enum Instruction {
    FORWARD('F'),
    LEFT('L'),
    RIGHT('R');

    private final char label;

    Instruction(final char label) {
        this.label = label;
    }

    public static Instruction fromValue(char label) {
        return Arrays.stream(values())
                .filter(instruction -> Character.toLowerCase(instruction.label) == Character.toLowerCase(label))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(label);
    }
}
