package com.kubiappi.bonuses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.Entites.Player;
import com.kubiappi.Entites.Teacher;
import com.kubiappi.info.GameInfo;

import java.util.Random;

/**
 * Created by Kuba Szczepaniak on 2017-04-02.
 */
public abstract class BonusMain{

    protected Texture texture;
    protected BonusType bonusType;
    protected float timer;
    protected Circle collisionCircle;
    protected boolean bonusThrow;

    public BonusMain(){

    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public abstract void timer();

//    public void timer(){
//        timer += Gdx.graphics.getDeltaTime();
//        if(timer > 5){
//            collisionCircle = null;
//        }
//    }

    public boolean playerCollision(Player player){
        Rectangle playerRectangle = player.getCollisionRectanglePlayer();
        if(collisionCircle != null)
            return Intersector.overlaps(collisionCircle,playerRectangle);
        else
            return false;
    }

    public boolean teacherCollision(Teacher teacher){
        Rectangle teacherRectangle = teacher.getCollisionGroundRectangle();
        if(collisionCircle != null)
            return Intersector.overlaps(collisionCircle,teacherRectangle);
        else
            return false;
    }

    public void createCircle(Vector2 position){
        collisionCircle = new Circle(position, GameInfo.BONUS_RADIUS);
    }

    public boolean CircleIsALive(){
        if(collisionCircle != null){
            return true;
        }else {
            return false;
        }
    }

    public void onPlayerCollision(Player player){
        if(!bonusThrow){
            this.collisionCircle.x =  player.getPosition().x + 100;
        }
        bonusThrow = true;
    }



    public Circle getCollisionCircle() {
        return collisionCircle;
    }

    public void deleteCircle() {
        collisionCircle = null;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition(){
        return new Vector2(collisionCircle.x,collisionCircle.y);
    }
}
