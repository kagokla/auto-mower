package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InstructionTest {

    @Test
    void testToString() {
        assertEquals("L", Instruction.LEFT.toString());
        assertEquals("R", Instruction.RIGHT.toString());
        assertEquals("F", Instruction.FORWARD.toString());
    }

    @Test
    void testFromValue() {
        assertEquals(Instruction.LEFT, Instruction.fromValue("L"));
        assertEquals(Instruction.RIGHT, Instruction.fromValue("R"));
        assertEquals(Instruction.FORWARD, Instruction.fromValue("F"));

        assertNull(Instruction.fromValue(""));
        assertNull(Instruction.fromValue("    "));
        assertNull(Instruction.fromValue("koko"));
    }
}