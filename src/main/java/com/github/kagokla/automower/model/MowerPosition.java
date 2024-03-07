package com.github.kagokla.automower.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MowerPosition {

    private int x;
    private int y;
    private Orientation orientation = Orientation.NORTH;

    public MowerPosition(MowerPosition position) {
        this.x = position.getX();
        this.y = position.getY();
        this.orientation = position.getOrientation();
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

    public void rotateLeft() {
        this.orientation = this.orientation.toLeft();
    }

    public void rotateRight() {
        this.orientation = this.orientation.toRight();
    }
}
