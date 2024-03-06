package com.github.kagokla.automower.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.kagokla.automower.model.Instruction;
import com.github.kagokla.automower.model.LawnArea;
import com.github.kagokla.automower.model.MowerPosition;
import com.github.kagokla.automower.model.converter.InstructionsConverter;
import com.github.kagokla.automower.model.converter.LawnAreaConverter;
import com.github.kagokla.automower.model.converter.MowerPositionConverter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommandResponseDTO {

    @JsonSerialize(converter = LawnAreaConverter.class)
    private LawnArea area;

    private List<Mower> mowers;

    @Getter
    @Setter
    public static class Mower {

        @JsonSerialize(converter = MowerPositionConverter.class)
        private MowerPosition initialPosition;

        @JsonSerialize(converter = InstructionsConverter.class)
        private List<Instruction> instructions;

        @JsonSerialize(converter = MowerPositionConverter.class)
        private MowerPosition finalPosition;
    }
}
