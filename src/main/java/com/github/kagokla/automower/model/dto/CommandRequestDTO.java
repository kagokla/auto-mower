package com.github.kagokla.automower.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommandRequestDTO {

    @NotNull
    @Pattern(regexp = "^\\s*\\d\\s*\\d\\s*$")
    private String area;

    private List<@Valid Mower> mowers;

    @Getter
    @Setter
    public static class Mower {

        @NotNull
        @Pattern(regexp = "^\\s*\\d\\s*\\d\\s*[NEWS\\s]*$")
        private String initialPosition;

        @Pattern(regexp = "^[FLR\\s]*$")
        private String instructions;
    }
}
