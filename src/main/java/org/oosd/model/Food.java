package org.oosd.model;

public class Food extends GameEntity {

    public Food() {
        super();
        setX(Math.random() * Game.fieldWidth);
        setY(Math.random() * Game.fieldHeight);
        if (Math.random() > 0.5) { // 50% can move
            setDx(Math.random() * 4 - 2);
            setDy(Math.random() * 4 - 2);
        }
    }

    @Override
    void process() {
        processMove();
    }

    @Override
    public EntityType getType() {
        return EntityType.FOOD;
    }
}
