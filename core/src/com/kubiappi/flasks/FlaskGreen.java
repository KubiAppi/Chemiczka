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
import com.kubiappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-03-22.
 */
public class FlaskGreen extends FlaskMain {

    private Rectangle collisionRectangle;
    private Sound breakSound;

    public FlaskGreen(){
        breakSound = Gdx.audio.newSound(Gdx.files.internal("Lamp-Switch_On.mp3"));
        textureFlask = new Texture("green_flask.png");
        textureAfter = new Texture("green_after_static.png");
        flasktype = FlaskType.GREEN;
    }

    @Override
    public void onCollisionGround(Vector2 flaskPosition, int id) {
        this.id = id;
        position = flaskPosition;
        collisionRectangle = new Rectangle(position.x - 175,0, GameInfo.FLASK_GREEN_ACID_WIDTH,GameInfo.FLASK_GREEN_ACID_HEIGHT);
        breakSound.play();
    }

    @Override
    public void onCollisionPlayer(Vector2 flaskPosition) {

    }

    @Override
    public void lookTimer() {
        timer += Gdx.graphics.getDeltaTime();
        if(timer > 6f)
            collisionRectangle = null;
    }

    @Override
    public Circle getCollisionCircle() {
        return null;
    }

    @Override
    public Circle[] getCollisionCircles() {
        return new Circle[0];
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    @Override
    public boolean PlayerCollision(Player player) {
        Rectangle playerCollision = player.getCollisionRectanglePlayer();
        if(collisionRectangle != null)
            return Intersector.overlaps(playerCollision,collisionRectangle);
        else
            return false;
    }
}
