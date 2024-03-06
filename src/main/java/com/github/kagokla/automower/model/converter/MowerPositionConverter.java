package com.github.kagokla.automower.model.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.kagokla.automower.model.MowerPosition;

public class MowerPositionConverter extends StdConverter<MowerPosition, String> {

    @Override
    public String convert(final MowerPosition mowerPosition) {
        return (null != mowerPosition && null != mowerPosition.getOrientation())
                ? "" + mowerPosition.getX() + mowerPosition.getY() + mowerPosition.getOrientation()
                : null;
    }
}
