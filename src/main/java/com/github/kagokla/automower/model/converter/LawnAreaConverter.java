package com.github.kagokla.automower.model.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.kagokla.automower.model.LawnArea;

public class LawnAreaConverter extends StdConverter<LawnArea, String> {

    @Override
    public String convert(final LawnArea lawnArea) {
        return (null != lawnArea) ? "" + lawnArea.width() + lawnArea.height() : null;
    }
}
