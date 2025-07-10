package org.oosd.UI;

import javafx.scene.Node;
import org.oosd.model.Game;

public interface Screen {

    Node getScreen();
    void setRoute(String path,Screen screen);
}
