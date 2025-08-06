package org.oosd.UI.sprite;

import javafx.scene.Node;
import org.oosd.model.GameEntity;

public interface Sprite {
    public Node getNode();

    public GameEntity getEntity();

    public void update();
}
