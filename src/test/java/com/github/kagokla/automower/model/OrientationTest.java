package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrientationTest {

    @Test
    void shouldReturnStringRepresentation() {
        assertThat(Orientation.NORTH).hasToString("N");
        assertThat(Orientation.EAST).hasToString("E");
        assertThat(Orientation.WEST).hasToString("W");
        assertThat(Orientation.SOUTH).hasToString("S");
    }

    @Test
    void shouldReturnEnumFromStringValue() {
        assertThat(Orientation.fromValue('N')).isEqualTo(Orientation.NORTH);
        assertThat(Orientation.fromValue('E')).isEqualTo(Orientation.EAST);
        assertThat(Orientation.fromValue('W')).isEqualTo(Orientation.WEST);
        assertThat(Orientation.fromValue('S')).isEqualTo(Orientation.SOUTH);
    }

    @Test
    void shouldReturnNullFromStringValue() {
        assertThat(Orientation.fromValue(Character.MIN_VALUE)).isNull();
        assertThat(Instruction.fromValue(Character.MAX_VALUE)).isNull();
        assertThat(Orientation.fromValue('k')).isNull();
    }

    @Test
    void shouldReturnOrientationToItsLeft() {
        assertThat(Orientation.EAST.toLeft()).isEqualTo(Orientation.NORTH);
        assertThat(Orientation.SOUTH.toLeft()).isEqualTo(Orientation.EAST);
        assertThat(Orientation.NORTH.toLeft()).isEqualTo(Orientation.WEST);
        assertThat(Orientation.WEST.toLeft()).isEqualTo(Orientation.SOUTH);
    }

    @Test
    void shouldReturnOrientationToItsRight() {
        assertThat(Orientation.WEST.toRight()).isEqualTo(Orientation.NORTH);
        assertThat(Orientation.NORTH.toRight()).isEqualTo(Orientation.EAST);
        assertThat(Orientation.SOUTH.toRight()).isEqualTo(Orientation.WEST);
        assertThat(Orientation.EAST.toRight()).isEqualTo(Orientation.SOUTH);
    }
}