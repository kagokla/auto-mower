package com.github.kagokla.automower.model.dto;

import com.github.kagokla.automower.model.ValidatorBase;
import org.junit.jupiter.api.Test;

import java.util.List;

class CommandRequestDTOTest extends ValidatorBase<CommandRequestDTO> {

    @Test
    void shouldSucceedWhenCommandRequestIsValid() {
        final var commandRequestDTO = buildDefaultCommandRequestDTO();
        isValid(commandRequestDTO);
    }

    @Test
    void shouldFailWhenMowerAreaIsInvalid() {
        final var commandRequestDTO = buildDefaultCommandRequestDTO();
        commandRequestDTO.setArea("area");
        isInvalid(commandRequestDTO, 1);
    }

    @Test
    void shouldFailWhenMowerAreaIsNull() {
        final var commandRequestDTO = buildDefaultCommandRequestDTO();
        commandRequestDTO.setArea(null);
        isInvalid(commandRequestDTO, 1);
    }

    @Test
    void shouldFailWhenInitialPositionIsInvalid() {
        final var mower = buildDefaultMower();
        mower.setInitialPosition("12D");
        final var commandRequestDTO = buildDefaultCommandRequestDTO(List.of(mower));
        isInvalid(commandRequestDTO, 1);
    }

    @Test
    void shouldFailWhenInitialPositionIsNull() {
        final var mower = buildDefaultMower();
        mower.setInitialPosition(null);
        final var commandRequestDTO = buildDefaultCommandRequestDTO(List.of(mower));
        isInvalid(commandRequestDTO, 1);
    }

    @Test
    void shouldFailWhenInstructionsAreInvalid() {
        final var mower = buildDefaultMower();
        mower.setInstructions("LFLFLFLBB");
        final var commandRequestDTO = buildDefaultCommandRequestDTO(List.of(mower));
        isInvalid(commandRequestDTO, 1);
    }

    @Test
    void shouldFailWhenInstructionsAreNull() {
        final var mower = buildDefaultMower();
        mower.setInstructions(null);
        final var commandRequestDTO = buildDefaultCommandRequestDTO(List.of(mower));
        isInvalid(commandRequestDTO, 1);
    }

    private CommandRequestDTO buildDefaultCommandRequestDTO() {
        final var commandRequest = new CommandRequestDTO();
        commandRequest.setArea("55");
        commandRequest.setMowers(List.of(buildDefaultMower()));

        return commandRequest;
    }

    private CommandRequestDTO buildDefaultCommandRequestDTO(List<CommandRequestDTO.Mower> mowers) {
        final var commandRequest = new CommandRequestDTO();
        commandRequest.setArea("55");
        commandRequest.setMowers(List.copyOf(mowers));

        return commandRequest;
    }

    private CommandRequestDTO.Mower buildDefaultMower() {
        final var mower = new CommandRequestDTO.Mower();
        mower.setInitialPosition("12N");
        mower.setInstructions("LFLFLFLFF");

        return mower;
    }
}