package main.util;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.enums.DirectionEnum;

public class Tombol {
    private DirectionEnum direction;

    public Tombol(DirectionEnum direction) {
        this.direction = direction;
    }

    /**
     * @return the direction
     */
    public DirectionEnum getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public void handleKeyPressed(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key == KeyCode.LEFT) {
            direction = DirectionEnum.LEFT;
        }
        if (key == KeyCode.RIGHT) {
            direction = DirectionEnum.RIGHT;
        }
    }

    public void handleKeyReleased(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key == KeyCode.LEFT) {
            direction = DirectionEnum.NONE;
        }
        if (key == KeyCode.RIGHT) {
            direction = DirectionEnum.NONE;
        }
    }
}