package com.kubiappi.flasks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.Entites.Player;
import com.kubiappi.enums.FlaskType;
import com.kubiappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-03-30.
 */
public class FlaskYellow extends FlaskMain {

    private Circle[] collisionCircles = new Circle[4];

    public FlaskYellow(){
        textureFlask = new Texture("yellow_flask.png");
        textureAfter = new Texture("red_after_static.png");
        flasktype = FlaskType.YELLOW;
    }

    @Override
    public void onCollisionGround(Vector2 flaskPosition, int id) {
        this.id = id;
        float[] radiuses = {GameInfo.FLASK_YELLOW_BIG_RADIUS, GameInfo.FLASK_YELLOW_BIG_RADIUS,GameInfo.FLASK_YELLOW_SMALL_RADIUS,GameInfo.FLASK_YELLOW_SMALL_RADIUS};
        for(int i = 0; i < 4;i++){
            collisionCircles[i] = new Circle(flaskPosition,  radiuses[i]);
        }
    }

    @Override
    public void onCollisionPlayer(Vector2 flaskPosition) {

    }

    @Override
    public void lookTimer() {
        timer += Gdx.graphics.getDeltaTime();
        if(collisionCircles != null){
            if(timer > 0.5f){
                for (int i = 0; i<4;i++){
                    collisionCircles[i].y -= 100f * Gdx.graphics.getDeltaTime();
                    switch (i){
                        case 0:
                            collisionCircles[i].x -= 100f * Gdx.graphics.getDeltaTime();
                            break;
                        case 1:
                            collisionCircles[i].x += 100f * Gdx.graphics.getDeltaTime();
                            break;
                        case 2:
                            collisionCircles[i].x -= 200f * Gdx.graphics.getDeltaTime();
                            break;
                        case 3:
                            collisionCircles[i].x += 200f * Gdx.graphics.getDeltaTime();
                            break;
                    }
    //                if(i == 1 || i == 3){
    //                    collisionCircles[i].x += 100f * Gdx.graphics.getDeltaTime();
    //                }else{
    //                    collisionCircles[i].x -= 100f * Gdx.graphics.getDeltaTime();
    //                }
                }
            }else{
                for (int i = 0; i<4;i++){
                    switch (i){
                        case 0:
                            collisionCircles[i].y += 100f * Gdx.graphics.getDeltaTime();
                            collisionCircles[i].x -= 100f * Gdx.graphics.getDeltaTime();
                            break;
                        case 1:
                            collisionCircles[i].y += 100f * Gdx.graphics.getDeltaTime();
                            collisionCircles[i].x += 100f * Gdx.graphics.getDeltaTime();
                            break;
                        case 2:
                            collisionCircles[i].y += 200f * Gdx.graphics.getDeltaTime();
                            collisionCircles[i].x -= 200f * Gdx.graphics.getDeltaTime();
                            break;
                        case 3:
                            collisionCircles[i].y += 200f * Gdx.graphics.getDeltaTime();
                            collisionCircles[i].x += 200f * Gdx.graphics.getDeltaTime();
                            break;
                    }
                }
            }
        }
        if(timer > 8f){
            collisionCircles = null;
        }
    }

    @Override
    public Circle getCollisionCircle() {
        return null;
    }

    public Circle[] getCollisionCircles() {
        return collisionCircles;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return null;
    }

    @Override
    public boolean PlayerCollision(Player player) {
        Rectangle playerCollison = player.getCollisionRectanglePlayer();
        boolean collisonTure = false;
        for(int i=0; i<4;i++){
            if(Intersector.overlaps(collisionCircles[i],playerCollison))
                collisonTure = true;
        }
        return collisonTure;
    }
}
