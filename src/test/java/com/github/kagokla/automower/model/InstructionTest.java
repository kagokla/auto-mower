package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InstructionTest {

    @Test
    void shouldReturnStringRepresentation() {
        assertThat(Instruction.LEFT).hasToString("L");
        assertThat(Instruction.RIGHT).hasToString("R");
        assertThat(Instruction.FORWARD).hasToString("F");
    }

    @Test
    void shouldReturnEnumFromStringValue() {
        assertThat(Instruction.fromValue('L')).isEqualTo(Instruction.LEFT);
        assertThat(Instruction.fromValue('R')).isEqualTo(Instruction.RIGHT);
        assertThat(Instruction.fromValue('F')).isEqualTo(Instruction.FORWARD);
    }

    @Test
    void shouldReturnNullFromStringValue() {
        assertThat(Instruction.fromValue(Character.MIN_VALUE)).isNull();
        assertThat(Instruction.fromValue(Character.MAX_VALUE)).isNull();
        assertThat(Instruction.fromValue('K')).isNull();
    }
}