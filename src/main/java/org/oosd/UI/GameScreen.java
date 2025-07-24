package org.oosd.UI;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import org.oosd.model.Game;

public class GameScreen implements ScreenWithGame {
    private Game game;
    private Pane gamePane;
    private BorderPane borderPane;
    private Screen mainScreen;
    private Frame parent;
    private AnimationTimer timer;
    private Circle ball;

    public GameScreen(Frame frame) {
        parent = frame;
        gamePane = new Pane();
        buildScreen();
        gamePane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                buildGamePane();
                setControl(newScene);
                startGame();
            }
        });
    }

    private void setControl(Scene scene) {
        // Key control
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                game.increaseY();
            } else if (e.getCode() == KeyCode.DOWN) {
                game.decreaseY();
            } else if (e.getCode() == KeyCode.LEFT) {
                game.increaseX();
            } else if (e.getCode() == KeyCode.RIGHT) {
                game.decreaseX();
            }
        });

    }

    private void startGame() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double nextX = ball.getCenterX() + game.getDx();
                double nextY = ball.getCenterY() + game.getDy();
                // Bounce off edges
                if (nextX - ball.getRadius() < 0 || nextX + ball.getRadius() > Game.fieldWidth) {
                    game.setDx(-game.getDx());
                }
                if (nextY - ball.getRadius() < 0 || nextY + ball.getRadius() > Game.fieldHeight) {
                    game.setDy(-game.getDy());
                }

                ball.setCenterX(ball.getCenterX() + game.getDx());
                ball.setCenterY(ball.getCenterY() + game.getDy());
            }
        };
        timer.start();

    }

    private void buildGamePane() {
        // Create red ball
        ball = new Circle(game.getSize(), game.getColor());
        ball.setCenterX(Game.fieldWidth / 2);
        ball.setCenterY(Game.fieldHeight / 2);
        if (game.isHasShadow()) {
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            ball.setEffect(shadow);
        }
        gamePane.getChildren().setAll(ball);
        gamePane.requestFocus();  // Ensure pane gets key input

    }

    private StackPane getBottomPane() {
        Button back = new Button("Back");
        back.setOnAction(e -> {
            timer.stop();
            parent.showScreen(mainScreen);
        });
        back.getStyleClass().add("menu-button");

        StackPane bottomPane = new StackPane(back);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(0, 0, 20, 0));
        return bottomPane;
    }


    private void buildScreen() {
        borderPane = new BorderPane();
        // Create field border

        borderPane.setTop(gamePane);
        borderPane.setBottom(getBottomPane());


    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Node getScreen() {
        return borderPane;
    }

    @Override
    public void setRoute(String path, Screen screen) {
        if ("back".equals(path)) {
            mainScreen = screen;
        }

    }
}
