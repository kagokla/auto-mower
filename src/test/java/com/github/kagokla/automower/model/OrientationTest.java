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
}