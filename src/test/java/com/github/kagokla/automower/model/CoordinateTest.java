package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateTest {

    @Test
    void shouldIncrementX() {
        final var coordinate = new Coordinate(5, 20);

        coordinate.incrementX();
        assertEquals(6, coordinate.getX());
    }

    @Test
    void shouldDecrementX() {
        final var coordinate = new Coordinate();

        coordinate.decrementX();
        assertEquals(-1, coordinate.getX());
    }

    @Test
    void shouldIncrementY() {
        final var coordinate = new Coordinate();

        coordinate.incrementY();
        assertEquals(1, coordinate.getY());
    }

    @Test
    void shouldDecrementY() {
        final var coordinate = new Coordinate(6, -4);

        coordinate.decrementY();
        assertEquals(-5, coordinate.getY());
    }
}