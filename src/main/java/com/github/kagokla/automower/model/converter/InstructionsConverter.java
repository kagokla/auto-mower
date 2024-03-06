package com.github.kagokla.automower.model.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.kagokla.automower.model.Instruction;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class InstructionsConverter extends StdConverter<List<Instruction>, String> {
    @Override
    public String convert(final List<Instruction> value) {
        return CollectionUtils.emptyIfNull(value).stream()
                .map(Instruction::toString)
                .collect(Collectors.joining());
    }
}
