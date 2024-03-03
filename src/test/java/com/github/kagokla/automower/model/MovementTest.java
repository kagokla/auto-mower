package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MovementTest {

    @Test
    void testToString() {
        assertEquals("L", Movement.LEFT.toString());
        assertEquals("R", Movement.RIGHT.toString());
        assertEquals("F", Movement.FORWARD.toString());
    }

    @Test
    void testFromValue() {
        assertEquals(Movement.LEFT, Movement.fromValue("L"));
        assertEquals(Movement.RIGHT, Movement.fromValue("R"));
        assertEquals(Movement.FORWARD, Movement.fromValue("F"));

        assertNull(Movement.fromValue(""));
        assertNull(Movement.fromValue("    "));
        assertNull(Movement.fromValue("koko"));
    }
}