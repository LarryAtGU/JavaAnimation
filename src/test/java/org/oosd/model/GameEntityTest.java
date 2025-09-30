package org.oosd.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameEntityTest {

    private GameEntity entity;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        entity = new GameEntity() {
            @Override
            void process() {

            }

            @Override
            public EntityType getType() {
                return null;
            }
        };
    }


    @org.junit.jupiter.api.Test
    void isDead() {
        entity.setLife(-1);
        assertFalse(entity.isDead());
        entity.setLife(0);
        assertTrue(entity.isDead());
    }

    @ParameterizedTest
    @CsvSource({
            "-10.0,0.0,5.0,false",
            "-1.0,0.0,5.0,true",
            "-1.0,-10.0,5.0,false",
            "1.0,10.0,5.0,true",
    })
    void isVisible(double x, double y, double size, boolean expected) {
        entity.setX(x);
        entity.setY(y);
        entity.setSize(size);
        assertEquals(entity.isVisible(), expected);

    }


    @org.junit.jupiter.api.Test
    void setX() {
        entity.setX(10.0);
        assertEquals(entity.getX(), 10.0);
    }
}