package org.oosd.UI.sprite;

import org.oosd.model.Game;
import org.oosd.model.GameEntity;
import org.oosd.model.Player;

import javax.swing.text.html.parser.Entity;
import java.util.*;

public class SpriteFactory {

    private List<Sprite> sprites;
    private List<GameEntity> entities;

    public void initFactory() {
        sprites = new ArrayList<>();
        entities = new ArrayList<>();
    }

    public synchronized void addEntity(GameEntity entity) {
        entities.add(entity);
    }

    public synchronized List<Sprite> produceSprites() {
        if (entities.isEmpty()) return null;
        List<Sprite> retSprites = new ArrayList<>();
        while (!entities.isEmpty()) {
            GameEntity entity = entities.removeFirst();
            Sprite sprite = produceSprite(entity);
            sprites.add(sprite);
            retSprites.add(sprite);
        }
        return retSprites;
    }

    public synchronized void updateSprites() {
        for (Sprite sprite : sprites) sprite.update();
    }

    private SpriteFactory() {
    }

    private static class SingletonFactory {
        private static final SpriteFactory factory = new SpriteFactory();
    }

    public static SpriteFactory getFactory() {
        return SingletonFactory.factory;
    }


    private Sprite produceSprite(GameEntity entity) {
        SpriteType type = EntitySpriteMapper.getSpriteType(entity.getType());
        return switch (type) {
            case SpriteType.PLAYER -> new PlayerSprite((Player) entity);
            case SpriteType.STAR -> new StarSprite(entity);
        };
    }

}
