package org.oosd.controller;

import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oosd.model.Game;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    GameController controller;
    SpyGame spy;

    static class SpyGame extends Game {
        boolean MoveUpCalled = false;
        boolean MoveDownCalled = false;

        @Override
        public void increaseY() {
            MoveDownCalled = true;
        }

        @Override
        public void decreaseY() {
            MoveUpCalled = true;
        }

    }

    @BeforeEach
    void setUp() {
        spy = new SpyGame();
        controller = new GameController(spy);
    }

    @Test
    void receiveKeyPress() {
        assertFalse(spy.MoveUpCalled);
        controller.receiveKeyPress(KeyCode.UP);
        assertTrue(spy.MoveUpCalled);

        assertFalse(spy.MoveDownCalled);
        controller.receiveKeyPress(KeyCode.DOWN);
        assertTrue(spy.MoveDownCalled);


    }
}