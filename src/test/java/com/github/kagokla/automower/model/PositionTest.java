package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void shouldIncrementX() {
        final var position = new Position(5, 20, Orientation.WEST);
        position.incrementX();
        assertThat(position.getX()).isEqualTo(6);
    }

    @Test
    void shouldDecrementX() {
        final var position = new Position();
        position.decrementX();
        assertThat(position.getX()).isEqualTo(-1);
    }

    @Test
    void shouldIncrementY() {
        final var position = new Position();
        position.incrementY();
        assertThat(position.getY()).isEqualTo(1);
    }

    @Test
    void shouldDecrementY() {
        final var position = new Position(6, -4, Orientation.SOUTH);
        position.decrementY();
        assertThat(position.getY()).isEqualTo(-5);
    }
}