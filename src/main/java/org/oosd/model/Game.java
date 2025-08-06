package org.oosd.model;

import javafx.scene.paint.Color;
import org.oosd.UI.sprite.SpriteFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    static public final double fieldWidth = 400;
    static public final double fieldHeight = 270;
    private Player player;
    private int foodNum;
    private List<GameEntity> entities;

    public Game() {
        foodNum = 8;
    }

    public void initGame() {
        player = new Player();
        entities = new ArrayList<>();
        entities.add(player);
        SpriteFactory.getFactory().addEntity(player);
        player.setX(fieldWidth / 2);
        player.setY(fieldHeight / 2);
        initFoods();

    }

    private void initFoods() {
        for (int i = 0; i < foodNum; ++i) {
            Food food = new Food();
            entities.add(food);
            SpriteFactory.getFactory().addEntity(food);
        }
    }

    public List<GameEntity> getEntities() {
        return entities;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public boolean isHasShadow() {
        if (player == null) return false;
        return player.isHasShadow();
    }

    public void setHasShadow(boolean hasShadow) {
        player.setHasShadow(hasShadow);
    }

    public String getColorString() {
        if (player == null) return "RED";
        return player.getColorString();
    }

    public void setColorString(String colorString) {
        player.setColorString(colorString);
    }


    public int getSize() {
        if (player == null) return 10;
        return (int) player.getSize();
    }

    public void setSize(int size) {
        player.setSize(size);
    }


    public void proceed() {
        for (GameEntity entity : entities) entity.process();
    }

    public void increaseX() {
        player.increaseX();
    }

    public void decreaseX() {
        player.decreaseX();
    }

    public void increaseY() {
        player.increaseY();
    }

    public void decreaseY() {
        player.decreaseY();
    }
}
