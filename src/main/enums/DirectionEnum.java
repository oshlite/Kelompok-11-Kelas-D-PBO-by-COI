package main.enums;

public enum DirectionEnum {
    NONE(0, 0), 
    LEFT(-1, 0), 
    RIGHT(1, 0), 
    UP(0, -1), 
    DOWN(0, 1);

    private final int deltaX;
    private final int deltaY;

    DirectionEnum(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }public int getDeltaY() {
        return deltaY;
    }
}