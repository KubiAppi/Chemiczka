package com.kubiappi.Entites;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Kuba Szczepaniak on 2017-03-16.
 */
public class Ground {

    private Rectangle collisionGroundRectangle;

    public Ground(){
        collisionGroundRectangle = new Rectangle(-5,0,900,5);
    }

    public Rectangle getCollisionGroundRectangle() {
        return collisionGroundRectangle;
    }
}
