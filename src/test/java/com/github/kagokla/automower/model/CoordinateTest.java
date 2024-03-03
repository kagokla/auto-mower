package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateTest {

    @Test
    void testIncrementX() {
        final var coordinate = new Coordinate(5, 20);

        coordinate.incrementX();
        assertEquals(6, coordinate.getX());
    }

    @Test
    void testDecrementX() {
        final var coordinate = new Coordinate();

        coordinate.decrementX();
        assertEquals(-1, coordinate.getX());
    }

    @Test
    void testIncrementY() {
        final var coordinate = new Coordinate();

        coordinate.incrementY();
        assertEquals(1, coordinate.getY());
    }

    @Test
    void testDecrementY() {
        final var coordinate = new Coordinate(6, -4);

        coordinate.decrementY();
        assertEquals(-5, coordinate.getY());
    }
}