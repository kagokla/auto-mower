package com.github.kagokla.automower.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Coordinate extends Point {

    public Coordinate(int x, int y) {
        super(x, y);
    }

    public Coordinate() {
        super();
    }

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
}
