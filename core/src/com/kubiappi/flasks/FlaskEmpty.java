package com.kubiappi.flasks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.Entites.Player;
import com.kubiappi.enums.FlaskType;

/**
 * Created by Kuba Szczepaniak on 2017-03-29.
 */
public class FlaskEmpty extends FlaskMain  {

    public FlaskEmpty(){
        textureFlask = new Texture("empty_flask.png");
        textureAfter = new Texture("empty.png");
        flasktype = FlaskType.EMPTY;
    }

    @Override
    public void onCollisionGround(Vector2 flaskPosition, int id) {

    }

    @Override
    public void onCollisionPlayer(Vector2 flaskPosition) {

    }

    @Override
    public void lookTimer() {

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
        return null;
    }

    @Override
    public boolean PlayerCollision(Player player) {
      return false;
    }
}
