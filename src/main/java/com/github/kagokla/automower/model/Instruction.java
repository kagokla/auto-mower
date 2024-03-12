package com.github.kagokla.automower.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mower's instruction. The mower can move forward or rotate according to an instruction.
 */
@Getter
public enum Instruction {
    FORWARD('F'),
    LEFT('L'),
    RIGHT('R');

    private static final Map<Character, Instruction> labelLookup =
            Arrays.stream(values()).collect(Collectors.toUnmodifiableMap(Instruction::getLabel, Function.identity()));
    private final char label;
    Instruction(final char label) {
        this.label = label;
    }

    public static Instruction fromLabel(final char label) {
        return labelLookup.get(Character.toUpperCase(label));
    }

    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(label);
    }
}
