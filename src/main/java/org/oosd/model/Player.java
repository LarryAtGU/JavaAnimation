package org.oosd.model;

import javafx.scene.paint.Color;

public class Player extends GameEntity {
    private boolean hasShadow = false;
    private String colorString = "RED";
    static final int MAX_SPEED = 5;

    public Player() {
        super();
        setSize(10);
        setDx(1);
        setDy(1);
    }

    public void increaseX() {
        setDy(0);
        setDx(Math.min(getDx() + 1, MAX_SPEED));
    }

    public void decreaseX() {
        setDy(0);
        setDx(Math.max(getDx() - 1, -MAX_SPEED));
    }

    public void increaseY() {
        setDx(0);
        setDy(Math.min(getDy() + 1, MAX_SPEED));
    }

    public void decreaseY() {
        setDx(0);
        setDy(Math.max(getDy() - 1, -MAX_SPEED));
    }

    public Color getColor() {
        return switch (getColorString()) {
            case "RED" -> Color.RED;
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            default -> Color.RED;
        };
    }

    public boolean isHasShadow() {
        return hasShadow;
    }

    public void setHasShadow(boolean hasShadow) {
        this.hasShadow = hasShadow;
    }

    public String getColorString() {
        return colorString;
    }

    public void setColorString(String colorString) {
        this.colorString = colorString;
    }

    @Override
    void process() {
        processMove();
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }
}
