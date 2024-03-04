package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LawnTest extends ValidatorTestBase<Lawn> {

    @SuppressWarnings("squid:S2699") // Assertions handled by ValidatorTestBase
    @Test
    void shouldSucceedWhenDimensionsAreValid() {
        final var lawn = new Lawn(10, 20);
        final var lawnWithMinimalDimensions = new Lawn(1, 1);

        isValid(lawn);
        isValid(lawnWithMinimalDimensions);
    }

    @SuppressWarnings("squid:S2699") // Assertions handled by ValidatorTestBase
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
        assertThat(lawn.isWiderThanPosition(5)).isTrue();
    }

    @Test
    void shouldFailWhenPositionIsOutsideLawnWidth() {
        final var lawn = new Lawn(10, 20);
        assertThat(lawn.isWiderThanPosition(20)).isFalse();
    }

    @Test
    void shouldSucceedWhenPositionIsInsideLawnHeight() {
        final var lawn = new Lawn(2011, 2018);
        assertThat(lawn.isLongerThanPosition(1984)).isTrue();
    }

    @Test
    void shouldFailWhenPositionIsOutsideLawnHeight() {
        final var lawn = new Lawn(2011, 2018);
        assertThat(lawn.isLongerThanPosition(2024)).isFalse();
    }
}