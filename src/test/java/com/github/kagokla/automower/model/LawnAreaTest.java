package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LawnAreaTest extends ValidatorTestBase<LawnArea> {

    @SuppressWarnings("squid:S2699") // Assertions handled by ValidatorTestBase
    @Test
    void shouldSucceedWhenDimensionsAreValid() {
        final var lawnArea = new LawnArea(10, 20);
        final var lawnWithMinimalDimensions = new LawnArea(1, 1);

        isValid(lawnArea);
        isValid(lawnWithMinimalDimensions);
    }

    @SuppressWarnings("squid:S2699") // Assertions handled by ValidatorTestBase
    @Test
    void shouldFailWhenDimensionsAreInvalid() {
        final var lawnAreaWithInvalidWidthAndHeight = new LawnArea(0, 0);
        final var lawnAreaWithInvalidWidth = new LawnArea(-1, 150);
        final var lawnAreaWithInvalidHeight = new LawnArea(300, 0);

        isInvalid(lawnAreaWithInvalidWidthAndHeight, 2);
        isInvalid(lawnAreaWithInvalidWidth, 1);
        isInvalid(lawnAreaWithInvalidHeight, 1);
    }

    @Test
    void shouldSucceedWhenPositionIsInsideLawnWidth() {
        final var lawnArea = new LawnArea(10, 20);
        assertThat(lawnArea.isWiderThanPosition(5)).isTrue();
    }

    @Test
    void shouldFailWhenPositionIsOutsideLawnWidth() {
        final var lawnArea = new LawnArea(10, 20);
        assertThat(lawnArea.isWiderThanPosition(20)).isFalse();
    }

    @Test
    void shouldSucceedWhenPositionIsInsideLawnHeight() {
        final var lawnArea = new LawnArea(2011, 2018);
        assertThat(lawnArea.isLongerThanPosition(1984)).isTrue();
    }

    @Test
    void shouldFailWhenPositionIsOutsideLawnHeight() {
        final var lawnArea = new LawnArea(2011, 2018);
        assertThat(lawnArea.isLongerThanPosition(2024)).isFalse();
    }
}