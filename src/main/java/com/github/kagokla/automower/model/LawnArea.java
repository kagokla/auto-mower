package com.github.kagokla.automower.model;

import jakarta.validation.constraints.Min;

/**
 * The aea of the lawn to be mowed by the automatic mower
 *
 * @param width Width of the lawn to be mowed
 * @param height Height of the lawn to be mowed
 */
public record LawnArea(@Min(1) int width, @Min(1) int height) {

    public boolean isWiderThanPosition(final int position) {
        return position < this.width;
    }

    public boolean isLongerThanPosition(final int position) {
        return position < this.height;
    }
}
