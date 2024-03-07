package com.github.kagokla.automower.model.mapper;

import com.github.kagokla.automower.model.Instruction;
import com.github.kagokla.automower.model.LawnArea;
import com.github.kagokla.automower.model.MowerPosition;
import com.github.kagokla.automower.model.Orientation;
import com.github.kagokla.automower.model.dto.CommandRequestDTO;
import com.github.kagokla.automower.model.dto.CommandResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper
public interface CommandRequestMapper {

    CommandRequestMapper MAPPER = Mappers.getMapper(CommandRequestMapper.class);

    CommandResponseDTO mapCommandRequestToCommandResponse(CommandRequestDTO commandRequest);

    @Mapping(target = "finalPosition", ignore = true)
    CommandResponseDTO.Mower mapCommandRequestMowerToCommandResponseMower(CommandRequestDTO.Mower commandRequestMower);

    default LawnArea mapAreaToLawn(final String commandRequestArea) {
        if (StringUtils.isBlank(commandRequestArea)) {
            return null;
        }

        final var sanitizedArea = commandRequestArea.replace(" ", "");
        // TODO: enforce sanitizedArea complies to the expected pattern
        final var width = Character.getNumericValue(sanitizedArea.charAt(0));
        final var height = Character.getNumericValue(sanitizedArea.charAt(1));

        return new LawnArea(width, height);
    }

    default MowerPosition mapMowerInitialPositionToPosition(final String mowerPosition) {
        if (StringUtils.isBlank(mowerPosition)) {
            return null;
        }

        final var sanitizedMowerPosition = StringUtils.deleteWhitespace(mowerPosition);
        // TODO: enforce sanitizedMowerPosition complies to the expected pattern
        final var position = new MowerPosition();
        position.setX(Character.getNumericValue(sanitizedMowerPosition.charAt(0)));
        position.setY(Character.getNumericValue(sanitizedMowerPosition.charAt(1)));
        position.setOrientation(Orientation.fromValue(sanitizedMowerPosition.charAt(2)));

        return position;
    }

    default List<Instruction> mapMowerInstructionsToInstructions(final String mowerInstructions) {
        if (StringUtils.isBlank(mowerInstructions)) {
            return Collections.emptyList();
        }

        final var sanitizedMowerInstructions = StringUtils.deleteWhitespace(mowerInstructions);
        final var instructions = new ArrayList<Instruction>();
        for (var i = 0; i < sanitizedMowerInstructions.length(); i++) {
            final var instruction = Instruction.fromValue(sanitizedMowerInstructions.charAt(i));
            instructions.add(instruction);
        }

        return Collections.unmodifiableList(instructions);
    }
}
