package com.github.kagokla.automower.model.mapper;

import com.github.kagokla.automower.model.Instruction;
import com.github.kagokla.automower.model.Orientation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandRequestMapperTest {

    @Test
    void shouldMapCommandRequestAreaToLawn() {
        final var commandRequestArea = "5 6";

        final var lawn = CommandRequestMapper.MAPPER.mapAreaToLawn(commandRequestArea);

        assertThat(lawn).isNotNull();
        assertThat(lawn.height()).isEqualTo(6);
        assertThat(lawn.width()).isEqualTo(5);
    }

    @Test
    void shouldMapCommandRequestAreaNullToLawnNull() {
        final var area = CommandRequestMapper.MAPPER.mapAreaToLawn(null);

        assertThat(area).isNull();
    }

    @Test
    void shouldMapCommandRequestMowerInitialPositionToPosition() {
        final var mowerInitialPosition = "12N";

        final var mowerPosition = CommandRequestMapper.MAPPER.mapMowerInitialPositionToPosition(mowerInitialPosition);

        assertThat(mowerPosition).isNotNull();
        assertThat(mowerPosition.getX()).isEqualTo(1);
        assertThat(mowerPosition.getY()).isEqualTo(2);
        assertThat(mowerPosition.getOrientation()).isEqualTo(Orientation.NORTH);
    }

    @Test
    void shouldMapCommandRequestMowerInitialPositionNullToPositionNull() {
        final var mowerPosition = CommandRequestMapper.MAPPER.mapMowerInitialPositionToPosition(null);

        assertThat(mowerPosition).isNull();
    }

    @Test
    void shouldMapCommandRequestMowerInstructionsToInstructions() {
        final var mowerInstructions = "FLR";

        final var instructions = CommandRequestMapper.MAPPER.mapMowerInstructionsToInstructions(mowerInstructions);

        assertThat(instructions)
                .isNotEmpty()
                .hasSize(3)
                .containsExactly(Instruction.FORWARD, Instruction.LEFT, Instruction.RIGHT);
    }

    @Test
    void shouldMapCommandRequestMowerInstructionsNullToInstructionsNull() {
        final var mowerInstructions = CommandRequestMapper.MAPPER.mapMowerInstructionsToInstructions(null);

        assertThat(mowerInstructions).isEmpty();
    }
}