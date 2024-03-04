package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LawnTest extends ValidatorBase<Lawn> {

    @SuppressWarnings("squid:S2699") // Assertions handled by ValidatorBase
    @Test
    void shouldSucceedWhenDimensionsAreValid() {
        final var lawn = new Lawn(10, 20);
        final var lawnWithMinimalDimensions = new Lawn(1, 1);

        isValid(lawn);
        isValid(lawnWithMinimalDimensions);
    }

    @SuppressWarnings("squid:S2699") // Assertions handled by ValidatorBase
    @Test
    void shouldFailWhenDimensionsAreInvalid() {
        final var lawnWithInvalidWidthAndHeight = new Lawn(0, 0);
        final var lawnWithInvalidWidth = new Lawn(-1, 150);
        final var lawnWithInvalidHeight = new Lawn(300, 0);

        isInvalid(lawnWithInvalidWidthAndHeight, 2);
        isInvalid(lawnWithInvalidWidth, 1);
        isInvalid(lawnWithInvalidHeight, 1);
    }

    @Test
    void shouldSucceedWhenPositionIsInsideLawnWidth() {
        final var lawn = new Lawn(10, 20);
        assertTrue(lawn.isWiderThanPosition(5));
    }

    @Test
    void shouldFailWhenPositionIsOutsideLawnWidth() {
        final var lawn = new Lawn(10, 20);
        assertFalse(lawn.isWiderThanPosition(20));
    }

    @Test
    void shouldSucceedWhenPositionIsInsideLawnHeight() {
        final var lawn = new Lawn(2011, 2018);
        assertTrue(lawn.isLongerThanPosition(1984));
    }

    @Test
    void shouldFailWhenPositionIsOutsideLawnHeight() {
        final var lawn = new Lawn(2011, 2018);
        assertFalse(lawn.isLongerThanPosition(2024));
    }
}