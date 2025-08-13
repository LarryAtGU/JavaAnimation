package org.oosd.UI;

import javafx.animation.AnimationTimer;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.oosd.UI.sprite.Sprite;
import org.oosd.UI.sprite.SpriteFactory;
import org.oosd.UI.sprite.Star;
import org.oosd.model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GamePane extends Pane {
    private AnimationTimer timer;
    private Game game;
    private final List<Sprite> sprites;

    public GamePane() {
        sprites = new ArrayList<>();
    }

    private void removeDeadSprites() {
        List<Sprite> deadSprites = SpriteFactory.getFactory()
                .extractAndRemoveDeadSprites();
        if (deadSprites.isEmpty()) return;
        sprites.removeAll(deadSprites);
        getChildren().removeAll(
                deadSprites.stream().map(Sprite::getNode)
                        .toList()
        );
    }

    private void addSprites() {
        List<Sprite> list = SpriteFactory.getFactory().produceSprites();
        if (list == null) return;
        for (Sprite sprite : list) {
            sprites.add(sprite);
            getChildren().add(sprite.getNode());
        }
    }

    private void updateSprites() {
        for (Sprite sprite : sprites) sprite.update();
    }

    public void setGame(Game game) {
        this.game = game;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.proceed();
                addSprites();
                updateSprites();
                removeDeadSprites();

            }
        };

    }


    private void buildGamePane() {
        // Create field border
        Rectangle field = new Rectangle(0, 0, Game.fieldWidth, Game.fieldHeight);
        field.setFill(Color.TRANSPARENT);
        field.setStroke(Color.BLACK);
        Rectangle clip = new Rectangle(0, 0, Game.fieldWidth, Game.fieldHeight);
        setClip(clip);
        getChildren().clear();
        getChildren().add(field);
        requestFocus();  // Ensure pane gets key input
    }

    void stopGame() {
        timer.stop();
    }

    void startGame() {
        SpriteFactory.getFactory().initFactory();
        buildGamePane();
        game.initGame();
        timer.start();
    }
}
