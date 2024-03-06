package com.github.kagokla.automower.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    private int x;
    private int y;

    public void incrementX() {
        this.x++;
    }

    public void decrementX() {
        this.x--;
    }

    public void incrementY() {
        this.y++;
    }

    public void decrementY() {
        this.y--;
    }

    private Orientation orientation;
}
