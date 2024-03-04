package com.github.kagokla.automower.service;

import com.github.kagokla.automower.model.dto.CommandRequestDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        // TODO: Add remaining assertions for the command's response
    }

    @Test
    void shouldNotMoveMowerOutsideTheArea() {
        // Given
        final var commandRequest = new CommandRequestDTO();
        final var firstMower = buildMower("11N", "FFFFFFFF");
        final var secondMower = buildMower("00E", "FRF");
        commandRequest.setArea("33");
        commandRequest.setMowers(List.of(firstMower, secondMower));

        // When
        final var commandResponse = mowingService.processCommand(commandRequest);

        // Then
        assertThat(commandResponse).isNotNull();
        // TODO: Add remaining assertions for the command's response
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