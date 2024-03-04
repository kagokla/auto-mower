package com.github.kagokla.automower.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommandResponseDTO {

    private String area;
    private List<Mower> mowers;

    @Getter
    @Setter
    public static class Mower {

        private String initialPosition;
        private String instructions;
        private String finalPosition;
    }
}
