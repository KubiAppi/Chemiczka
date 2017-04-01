package com.kubiappi.Entites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.enums.FlaskType;
import com.kubiappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-03-16.
 */
public class Player {

    private Rectangle collisionRectanglePlayer;
    private int lives;

    private float x, y;
    private FlaskType lastCollision;
    private int lastId;
    private float speed;

    public Player(){
        collisionRectanglePlayer = new Rectangle(x,y, GameInfo.PLAYER_WIDTH,GameInfo.PLAYER_HEIGHT);
        lives = 5;
        speed = 200f;
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

    public void setLastCollision(FlaskType lastCollision) {
        this.lastCollision = lastCollision;
    }

    public FlaskType getLastCollision() {
        return lastCollision;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public int getLastId() {
        return lastId;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public float getSpeed(){
        return speed;
    }

    public int getLives() {
        return lives;
    }

    public void decrementLives(){
        lives -= 1;
    }
}
