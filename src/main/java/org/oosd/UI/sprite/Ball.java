package org.oosd.UI.sprite;

import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Ball extends Circle {
    public Ball(double size, Color color, boolean isHasShadow) {
        super(size, color);
        if (isHasShadow) {
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            setEffect(shadow);
        }
    }
}
