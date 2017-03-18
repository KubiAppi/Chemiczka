package com.kubiappi.Entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.info.GameInfo;

import java.util.Random;

/**
 * Created by Kuba Szczepaniak on 2017-03-16.
 */
public class Flask {

    private Circle collisionCircleFlask;
    public boolean newFlask;
    private Vector2 selectThrow;

    private Vector2 position;

    public Flask(){
        collisionCircleFlask = new Circle(GameInfo.FLASK_START_X, GameInfo.FLASK_START_Y,GameInfo.FLASK_RADIUS);
        position = new Vector2(collisionCircleFlask.x,collisionCircleFlask.y);
        newFlask = true;
    }

    public Circle getCollisionCircleFlask() {
        return collisionCircleFlask;
    }

    public void update(){
        position.x += selectThrow.x * Gdx.graphics.getDeltaTime();
        position.y -= selectThrow.y * Gdx.graphics.getDeltaTime();
        updateCollisionCircle();
    }
    public void throwFlask(){
        if(newFlask){
            selectThrow();
            newFlask = false;
        }
        position.x += selectThrow.x * 2 * Gdx.graphics.getDeltaTime();
        position.y += selectThrow.y * 2 * Gdx.graphics.getDeltaTime();
        updateCollisionCircle();
    }

    public void setPosition(Vector2 position){
        this.position = position;
        updateCollisionCircle();
    }

    public void updateCollisionCircle(){
        collisionCircleFlask.setPosition(position);
    }

    private void selectThrow(){
        Vector2[] throwlist = {GameInfo.FLASK_THROW_1,GameInfo.FLASK_THROW_2,GameInfo.FLASK_THROW_3,GameInfo.FLASK_THROW_4,GameInfo.FLASK_THROW_5};
        Random rand = new Random();
        selectThrow = throwlist[rand.nextInt(5)];
        System.out.println(selectThrow);
    }

    public boolean groundCollision(Ground ground){
        Rectangle groundCollision = ground.getCollisionGroundRectangle();
        return Intersector.overlaps(collisionCircleFlask,groundCollision);
    }

    public boolean playerCollision(Player player){
        Rectangle playerCollision = player.getCollisionRectanglePlayer();
        return Intersector.overlaps(collisionCircleFlask,playerCollision);
    }


}
