package com.github.kagokla.automower.service;

import com.github.kagokla.automower.model.MowerPosition;
import com.github.kagokla.automower.model.Orientation;
import com.github.kagokla.automower.model.dto.CommandRequestDTO;
import com.github.kagokla.automower.model.dto.CommandResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class MowingServiceTest {

    private final MowingService mowingService = new MowingServiceImpl();

    @Test
    void shouldMoveMowerInsideTheArea() {
        // Given
        final var commandRequest = new CommandRequestDTO();
        final var firstMower = buildMower("12N", "LFLFLFLFF");
        final var secondMower = buildMower("33E", "FFRFFRFRRF");
        commandRequest.setArea("55");
        commandRequest.setMowers(List.of(firstMower, secondMower));

        // When
        final var commandResponse = mowingService.processCommand(commandRequest);

        // Then
        assertThat(commandResponse).isNotNull();
        assertThat(commandResponse.getArea().width()).isEqualTo(5);
        assertThat(commandResponse.getArea().height()).isEqualTo(5);
        assertThat(commandResponse.getMowers()).hasSize(2);

        final Predicate<CommandResponseDTO.Mower> firstMowerPredicate = mower -> null != mower.getInitialPosition()
                && mower.getInitialPosition().getX() == 1
                && mower.getInitialPosition().getY() == 2
                && mower.getInitialPosition().getOrientation() == Orientation.NORTH;

        final Predicate<CommandResponseDTO.Mower> secondMowerPredicate = mower -> null != mower.getInitialPosition()
                && mower.getInitialPosition().getX() == 3
                && mower.getInitialPosition().getY() == 3
                && mower.getInitialPosition().getOrientation() == Orientation.EAST;

        assertThat(commandResponse.getMowers())
                .doesNotContainNull()
                .filteredOn(firstMowerPredicate)
                .extracting(CommandResponseDTO.Mower::getFinalPosition)
                .flatExtracting(MowerPosition::getX, MowerPosition::getY, MowerPosition::getOrientation)
                .containsExactly(1, 3, Orientation.NORTH);
        assertThat(commandResponse.getMowers())
                .filteredOn(secondMowerPredicate)
                .extracting(CommandResponseDTO.Mower::getFinalPosition)
                .flatExtracting(MowerPosition::getX, MowerPosition::getY, MowerPosition::getOrientation)
                .containsExactly(5, 1, Orientation.EAST);
    }

    @Test
    void shouldNotMoveMowerOutsideTheArea() {
        // Given
        final var commandRequest = new CommandRequestDTO();
        final var mower = buildMower("11N", "FFFFFFFF");
        commandRequest.setArea("33");
        commandRequest.setMowers(List.of(mower));

        // When
        final var commandResponse = mowingService.processCommand(commandRequest);

        // Then
        assertThat(commandResponse).isNotNull();
        assertThat(commandResponse.getArea().width()).isEqualTo(3);
        assertThat(commandResponse.getArea().height()).isEqualTo(3);
        assertThat(commandResponse.getMowers()).hasSize(1);
        assertThat(commandResponse.getMowers())
                .doesNotContainNull()
                .extracting(CommandResponseDTO.Mower::getFinalPosition)
                .isEqualTo("13N");
    }

    @Test
    void shouldReturnNullWhenRequestIsNull() {
        // Given

        // When
        final var commandResponse = mowingService.processCommand(null);

        // Then
        assertThat(commandResponse).isNull();
    }

    private CommandRequestDTO.Mower buildMower(final String initialPosition, final String instructions) {
        final var mower = new CommandRequestDTO.Mower();
        mower.setInitialPosition(initialPosition);
        mower.setInstructions(instructions);

        return mower;
    }
}