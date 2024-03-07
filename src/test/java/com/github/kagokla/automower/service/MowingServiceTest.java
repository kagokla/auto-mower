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
    void shouldNotMoveMowerBeyondTheAreaEast() {
        // Given
        final var commandRequest = new CommandRequestDTO();
        final var mower = buildMower("51E", "FFF");
        commandRequest.setArea("76");
        commandRequest.setMowers(List.of(mower));

        // When
        final var commandResponse = mowingService.processCommand(commandRequest);

        // Then
        assertThat(commandResponse).isNotNull();
        assertThat(commandResponse.getArea().width()).isEqualTo(7);
        assertThat(commandResponse.getArea().height()).isEqualTo(6);
        assertThat(commandResponse.getMowers()).hasSize(1);
        final var finalPosition = commandResponse.getMowers().getFirst().getFinalPosition();
        assertThat(finalPosition.getX()).isEqualTo(7);
        assertThat(finalPosition.getY()).isEqualTo(1);
        assertThat(finalPosition.getOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    void shouldNotMoveMowerBeyondTheAreaWest() {
        // Given
        final var commandRequest = new CommandRequestDTO();
        final var mower = buildMower("32W", "FFFF");
        commandRequest.setArea("99");
        commandRequest.setMowers(List.of(mower));

        // When
        final var commandResponse = mowingService.processCommand(commandRequest);

        // Then
        assertThat(commandResponse).isNotNull();
        assertThat(commandResponse.getArea().width()).isEqualTo(9);
        assertThat(commandResponse.getArea().height()).isEqualTo(9);
        assertThat(commandResponse.getMowers()).hasSize(1);

        final var finalPosition = commandResponse.getMowers().getFirst().getFinalPosition();
        assertThat(finalPosition.getX()).isZero();
        assertThat(finalPosition.getY()).isEqualTo(2);
        assertThat(finalPosition.getOrientation()).isEqualTo(Orientation.WEST);
    }

    @Test
    void shouldNotMoveMowerBeyondTheAreaNorth() {
        // Given
        final var commandRequest = new CommandRequestDTO();
        final var mower = buildMower("11N", "FFFFF");
        commandRequest.setArea("35");
        commandRequest.setMowers(List.of(mower));

        // When
        final var commandResponse = mowingService.processCommand(commandRequest);

        // Then
        assertThat(commandResponse).isNotNull();
        assertThat(commandResponse.getArea().width()).isEqualTo(3);
        assertThat(commandResponse.getArea().height()).isEqualTo(5);
        assertThat(commandResponse.getMowers()).hasSize(1);
        final var finalPosition = commandResponse.getMowers().getFirst().getFinalPosition();
        assertThat(finalPosition.getX()).isEqualTo(1);
        assertThat(finalPosition.getY()).isEqualTo(5);
        assertThat(finalPosition.getOrientation()).isEqualTo(Orientation.NORTH);
    }

    @Test
    void shouldNotMoveMowerBeyondTheAreaSouth() {
        // Given
        final var commandRequest = new CommandRequestDTO();
        final var mower = buildMower("22S", "FFF");
        commandRequest.setArea("44");
        commandRequest.setMowers(List.of(mower));

        // When
        final var commandResponse = mowingService.processCommand(commandRequest);

        // Then
        assertThat(commandResponse).isNotNull();
        assertThat(commandResponse.getArea().width()).isEqualTo(4);
        assertThat(commandResponse.getArea().height()).isEqualTo(4);
        assertThat(commandResponse.getMowers()).hasSize(1);

        final var finalPosition = commandResponse.getMowers().getFirst().getFinalPosition();
        assertThat(finalPosition.getX()).isEqualTo(2);
        assertThat(finalPosition.getY()).isZero();
        assertThat(finalPosition.getOrientation()).isEqualTo(Orientation.SOUTH);
    }

    @Test
    void shouldNotMoveMowerInitiallyOutsideTheArea() {
        // Given
        final var commandRequest = new CommandRequestDTO();
        final var mower = buildMower("54E", "FFFFF");
        commandRequest.setArea("22");
        commandRequest.setMowers(List.of(mower));

        // When
        final var commandResponse = mowingService.processCommand(commandRequest);

        // Then
        assertThat(commandResponse).isNotNull();
        assertThat(commandResponse.getArea().width()).isEqualTo(2);
        assertThat(commandResponse.getArea().height()).isEqualTo(2);
        assertThat(commandResponse.getMowers()).hasSize(1);

        final var finalPosition = commandResponse.getMowers().getFirst().getFinalPosition();
        assertThat(finalPosition).isNull();
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