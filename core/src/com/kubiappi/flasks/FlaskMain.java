package com.kubiappi.flasks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.Entites.Player;
import com.kubiappi.enums.FlaskType;

/**
 * Created by Kuba Szczepaniak on 2017-03-21.
 */
public abstract class FlaskMain {

    protected Texture textureAfter, textureFlask;
    protected FlaskType flasktype;
    int id;
    Vector2 position;
    float timer;

    public abstract void onCollisionGround(Vector2 flaskPosition, int id);
    public abstract void onCollisionPlayer(Vector2 flaskPosition);
    public abstract void lookTimer();
    public abstract Circle getCollisionCircle();
    public abstract Circle[] getCollisionCircles();
    public abstract Rectangle getCollisionRectangle();
    public abstract boolean PlayerCollision(Player player);

    public Vector2 getPosition(){
        return position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public FlaskType getFlasktype() {
        return flasktype;
    }

    public Texture getTextureAfter(){
        return textureAfter;
    }

    public Texture getTextureFlask() {
        return textureFlask;
    }
}
