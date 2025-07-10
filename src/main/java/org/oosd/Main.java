package org.oosd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.oosd.UI.*;
import org.oosd.model.Game;

import java.util.Optional;

public class Main extends Application implements Frame {

    private StackPane root;
    private Game game;

    private void buildScreens() {
        Screen mainScreen = new MainScreen(this);
        ScreenWithGame configScreen = new ConfigScreen(this);
        ScreenWithGame gameScreen = new GameScreen(this);

        mainScreen.setRoute("config", configScreen);
        mainScreen.setRoute("game", gameScreen);

        configScreen.setGame(game);
        configScreen.setRoute("back", mainScreen);

        gameScreen.setGame(game);
        gameScreen.setRoute("back", mainScreen);

        showScreen(mainScreen);
    }

    @Override
    public void start(Stage primaryStage) {
        game = new Game();
        root = new StackPane();
        Scene scene = new Scene(root, Game.fieldWidth, Game.fieldHeight);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setTitle("JavaFX Multi-Screen Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        buildScreens();
        primaryStage.setOnCloseRequest(event -> {
            event.consume(); // Stop the window from closing automatically
            showExitConfirmation();
        });
    }

    public void showScreen(Screen scr) {
        root.getChildren().setAll(scr.getScreen());
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void showExitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit?");
        // Block and wait for user response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

}
