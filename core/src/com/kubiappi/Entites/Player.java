package com.kubiappi.Entites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-03-16.
 */
public class Player {

    private Rectangle collisionRectanglePlayer;
    public int lives;

    private float x, y;

    public Player(){
        collisionRectanglePlayer = new Rectangle(x,y, GameInfo.PLAYER_WIDTH,GameInfo.PLAYER_HEIGHT);
        lives = 3;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        updateCollisionRectangle();
    }

    private void updateCollisionRectangle() {
        collisionRectanglePlayer.setY(y);
        collisionRectanglePlayer.setX(x);
    }

    public Vector2 getPosition(){
        return new Vector2(x,y);
    }

    public Rectangle getCollisionRectanglePlayer() {
        return collisionRectanglePlayer;
    }
}
