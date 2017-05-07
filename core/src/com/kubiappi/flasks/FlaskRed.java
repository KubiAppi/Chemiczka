package com.kubiappi.flasks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.Entites.Player;
import com.kubiappi.enums.FlaskType;
import com.kubiappi.flasks.FlaskMain;
import com.kubiappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-03-21.
 */
public class FlaskRed extends FlaskMain {

    private Circle collisionBoomCircle;
    private Sound explosiveSound;

    public FlaskRed(){
        explosiveSound = Gdx.audio.newSound(Gdx.files.internal("Explosion1.mp3"));
        textureFlask = new Texture("red_flask.png");
        textureAfter = new Texture("red_after_static.png");
        flasktype = FlaskType.RED;
    }


    public void onCollisionGround(Vector2 flaskPosition, int id) {
        this.id = id;
        explosiveSound.play();
        position = flaskPosition;
        collisionBoomCircle = new Circle(position, GameInfo.FLASK_RED_RADIUS);
    }

    public void onCollisionPlayer(Vector2 flaskPosition) {

    }
    public void lookTimer(){
        timer += Gdx.graphics.getDeltaTime();
        if(timer > 1.75f)
            collisionBoomCircle = null;
    }

    @Override
    public Circle getCollisionCircle() {
        return collisionBoomCircle;
    }

    @Override
    public Circle[] getCollisionCircles() {
        return new Circle[0];
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return null;
    }

    @Override
    public boolean PlayerCollision(Player player) {
        Rectangle playerRectangle = player.getCollisionRectanglePlayer();
        if(collisionBoomCircle != null)
            return Intersector.overlaps(collisionBoomCircle,playerRectangle);
        else
            return false;
    }
}
