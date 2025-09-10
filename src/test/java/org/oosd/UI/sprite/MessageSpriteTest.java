package org.oosd.UI.sprite;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oosd.model.Message;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MessageSpriteTest {
    Message message;
    MessageSprite sprite;
    private static final AtomicBoolean jfxStarted = new AtomicBoolean(false);

    @BeforeEach
    void setUp() {
        // This starts the JavaFX platform and returns immediately.
        if (jfxStarted.compareAndSet(false, true)) {
            Platform.startup(() -> {
            });
        }
        message = mock(Message.class);
        when(message.getMessage()).thenReturn("Hello, world!");
        sprite = new MessageSprite(message);
    }

    @Test
    void getNode() {
        Label lb = (Label) sprite.getNode();
        assertNotNull(lb);
        assertEquals(lb.getText(), "Hello, world!");
    }

    @Test
    void getEntity() {
        Message m = sprite.getEntity();
        assertSame(m, message);
    }
}