package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrientationTest {

    @Test
    void testToString() {
        assertEquals("N", Orientation.NORTH.toString());
        assertEquals("E", Orientation.EAST.toString());
        assertEquals("W", Orientation.WEST.toString());
        assertEquals("S", Orientation.SOUTH.toString());
    }

    @Test
    void testFromValue() {
        assertEquals(Orientation.NORTH, Orientation.fromValue("N"));
        assertEquals(Orientation.EAST, Orientation.fromValue("E"));
        assertEquals(Orientation.WEST, Orientation.fromValue("W"));
        assertEquals(Orientation.SOUTH, Orientation.fromValue("S"));

        assertNull(Orientation.fromValue(""));
        assertNull(Orientation.fromValue("    "));
        assertNull(Orientation.fromValue("koko"));
    }

    @Test
    void testToLeft() {
        assertEquals(Orientation.NORTH, Orientation.EAST.toLeft());
        assertEquals(Orientation.EAST, Orientation.SOUTH.toLeft());
        assertEquals(Orientation.WEST, Orientation.NORTH.toLeft());
        assertEquals(Orientation.SOUTH, Orientation.WEST.toLeft());
    }

    @Test
    void testToRight() {
        assertEquals(Orientation.NORTH, Orientation.WEST.toRight());
        assertEquals(Orientation.EAST, Orientation.NORTH.toRight());
        assertEquals(Orientation.WEST, Orientation.SOUTH.toRight());
        assertEquals(Orientation.SOUTH, Orientation.EAST.toRight());
    }
}