package com.github.kagokla.automower.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LawnTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        validatorFactory.close();
    }

    @Test
    void testLawnValidDimensions() {
        final var lawn = new Lawn(10, 20);
        final var lawnWithMinimalDimensions = new Lawn(1, 1);

        isValidLawn(lawn);
        isValidLawn(lawnWithMinimalDimensions);
    }

    @Test
    void testLawnInvalidDimensions() {
        final var lawnWithInvalidWidthAndHeight = new Lawn(0, 0);
        final var lawnWithInvalidWidth = new Lawn(-1, 150);
        final var lawnWithInvalidHeight = new Lawn(300, 0);

        isInvalidLawn(lawnWithInvalidWidthAndHeight, 2);
        isInvalidLawn(lawnWithInvalidWidth, 1);
        isInvalidLawn(lawnWithInvalidHeight, 1);
    }

    @Test
    void testIsWiderThanPosition() {
        final var lawn = new Lawn(10, 20);

        assertTrue(lawn.isWiderThanPosition(5));
        assertFalse(lawn.isWiderThanPosition(20));
    }

    @Test
    void testIsLongerThanPosition() {
        final var lawn = new Lawn(2011, 2018);

        assertTrue(lawn.isLongerThanPosition(1984));
        assertFalse(lawn.isLongerThanPosition(2024));
    }

    private void isValidLawn(final Lawn validLawn) {
        var violations = validator.validate(validLawn);
        assertTrue(violations.isEmpty());
    }

    private void isInvalidLawn(final Lawn invalidLawn, final int numberOfViolations) {
        var violations = validator.validate(invalidLawn);
        assertFalse(violations.isEmpty());
        assertEquals(numberOfViolations, violations.size());
    }
}