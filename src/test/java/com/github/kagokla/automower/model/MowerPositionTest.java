package com.github.kagokla.automower.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MowerPositionTest {

    @Test
    void shouldIncrementX() {
        final var position = new MowerPosition();
        position.setX(5);

        position.incrementX();

        assertThat(position.getX()).isEqualTo(6);
    }

    @Test
    void shouldDecrementX() {
        final var position = new MowerPosition();

        position.decrementX();

        assertThat(position.getX()).isEqualTo(-1);
    }

    @Test
    void shouldIncrementY() {
        final var position = new MowerPosition();

        position.incrementY();

        assertThat(position.getY()).isEqualTo(1);
    }

    @Test
    void shouldDecrementY() {
        final var position = new MowerPosition();
        position.setY(4);

        position.decrementY();

        assertThat(position.getY()).isEqualTo(3);
    }

    @Test
    void shouldRotateLeft() {
        final var position = new MowerPosition();
        position.rotateLeft();
        assertThat(position.getOrientation()).isEqualTo(Orientation.WEST);
    }

    @Test
    void shouldRotateRight() {
        final var position = new MowerPosition();
        position.rotateRight();
        assertThat(position.getOrientation()).isEqualTo(Orientation.EAST);
    }
}