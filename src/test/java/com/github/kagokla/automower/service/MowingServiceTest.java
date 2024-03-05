package com.github.kagokla.automower.service;

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
        assertThat(commandResponse.getArea()).isEqualTo(commandRequest.getArea());
        assertThat(commandResponse.getMowers()).hasSize(2);

        final Predicate<CommandResponseDTO.Mower> firstMowerPredicate =
                mower -> "12N".equalsIgnoreCase(mower.getInitialPosition())
                        && "LFLFLFLFF".equalsIgnoreCase(mower.getInstructions());
        final Predicate<CommandResponseDTO.Mower> secondMowerPredicate =
                mower -> "33E".equalsIgnoreCase(mower.getInitialPosition())
                        && "FFRFFRFRRF".equalsIgnoreCase(mower.getInstructions());

        assertThat(commandResponse.getMowers())
                .doesNotContainNull()
                .filteredOn(firstMowerPredicate)
                .extracting(CommandResponseDTO.Mower::getFinalPosition)
                .isEqualTo("13N");
        assertThat(commandResponse.getMowers())
                .filteredOn(secondMowerPredicate)
                .extracting(CommandResponseDTO.Mower::getFinalPosition)
                .isEqualTo("51E");
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
        assertThat(commandResponse.getArea()).isEqualTo(commandRequest.getArea());
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