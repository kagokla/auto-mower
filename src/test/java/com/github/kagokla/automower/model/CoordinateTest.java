package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateTest {

    @Test
    void shouldIncrementX() {
        final var coordinate = new Coordinate(5, 20);
        coordinate.incrementX();
        assertThat(coordinate.getX()).isEqualTo(6);
    }

    @Test
    void shouldDecrementX() {
        final var coordinate = new Coordinate();
        coordinate.decrementX();
        assertThat(coordinate.getX()).isEqualTo(-1);
    }

    @Test
    void shouldIncrementY() {
        final var coordinate = new Coordinate();
        coordinate.incrementY();
        assertThat(coordinate.getY()).isEqualTo(1);
    }

    @Test
    void shouldDecrementY() {
        final var coordinate = new Coordinate(6, -4);
        coordinate.decrementY();
        assertThat(coordinate.getY()).isEqualTo(-5);
    }
}