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
    void testRotateLeft() {
        assertEquals(Orientation.NORTH, Orientation.EAST.rotateLeft());
        assertEquals(Orientation.EAST, Orientation.SOUTH.rotateLeft());
        assertEquals(Orientation.WEST, Orientation.NORTH.rotateLeft());
        assertEquals(Orientation.SOUTH, Orientation.WEST.rotateLeft());
    }

    @Test
    void testRotateRight() {
        assertEquals(Orientation.NORTH, Orientation.WEST.rotateRight());
        assertEquals(Orientation.EAST, Orientation.NORTH.rotateRight());
        assertEquals(Orientation.WEST, Orientation.SOUTH.rotateRight());
        assertEquals(Orientation.SOUTH, Orientation.EAST.rotateRight());
    }
}