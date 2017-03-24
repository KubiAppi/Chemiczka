package com.kubiappi.flasks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.Entites.Player;
import com.kubiappi.enums.FlaskType;

/**
 * Created by Kuba Szczepaniak on 2017-03-22.
 */
public class FlaskGreen extends FlaskMain {

    private Rectangle collisionRectangle;

    public FlaskGreen(){
        //texture = new Texture("");
        flasktype = FlaskType.GREEN;
    }

    @Override
    public void onCollisionGround(Vector2 flaskPosition, int id) {
        this.id = id;
        position = flaskPosition;
        collisionRectangle = new Rectangle(position.x - 175,0,350,15);
    }

    @Override
    public void onCollisionPlayer(Vector2 flaskPosition) {

    }

    @Override
    public void lookTimer() {
        timer += Gdx.graphics.getDeltaTime();
        if(timer > 3f)
            collisionRectangle = null;
    }

    @Override
    public Circle getCollisionCircle() {
        return null;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    @Override
    public boolean PlayerCollision(Player player) {
        Rectangle playerCollision = player.getCollisionRectanglePlayer();
        return Intersector.overlaps(playerCollision,collisionRectangle);
    }
}