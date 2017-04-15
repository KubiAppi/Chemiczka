package com.kubiappi.Entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.flasks.*;
import com.kubiappi.enums.FlaskType;
import com.kubiappi.info.GameInfo;

import java.util.Random;

public class Flask {

    private Circle collisionCircleFlask;
    private boolean newFlask;
    private Vector2 selectThrow;
    private Vector2 position;
    private FlaskMain flaskSelection,oldFlask;
    private int id;
    private Texture oldFlaskTexture, nowFlaskTexture, oldAfterTexture, nowAfterTexture;

    public Flask(){
        collisionCircleFlask = new Circle(GameInfo.FLASK_START_X, GameInfo.FLASK_START_Y,GameInfo.FLASK_RADIUS);
        position = new Vector2(collisionCircleFlask.x,collisionCircleFlask.y);
        newFlask = true;
        randomSelectFlaskType();
    }

    public void update(){
        position.x += selectThrow.x * Gdx.graphics.getDeltaTime();
        position.y -= selectThrow.y * Gdx.graphics.getDeltaTime();
        updateCollisionCircle();
    }

    public void throwFlask(){
        if(newFlask){
            id++;
            selectThrow();
            newFlask = false;
        }
        position.x += selectThrow.x * 2 * Gdx.graphics.getDeltaTime();
        position.y += selectThrow.y * 2 * Gdx.graphics.getDeltaTime();
        updateCollisionCircle();
    }


    private void updateCollisionCircle(){
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



    public void randomSelectFlaskType(){
        Random rand = new Random();
        oldFlask = flaskSelection;
        oldFlaskTexture = nowFlaskTexture;
        oldAfterTexture = nowAfterTexture;
        switch (rand.nextInt(5)){
            case 0:
                flaskSelection = new FlaskRed();
                break;
            case 1:
                flaskSelection = new FlaskGreen();
                break;
            case 2:
                flaskSelection = new FlaskBlue();
                break;
            case 3:
                flaskSelection = new FlaskEmpty();
                break;
            case 4:
                flaskSelection = new FlaskYellow();
            default:
                flaskSelection = new FlaskYellow();

        }
        nowFlaskTexture = flaskSelection.getTextureAfter();
        nowFlaskTexture = flaskSelection.getTextureFlask();

    }

    public void setPosition(Vector2 position){
        this.position = position;
        updateCollisionCircle();
    }

    public boolean oldIsNull(){
        if(oldFlask == null)
            return true;
        else
            return false;
    }

    public boolean oldRectangleTrue(){
        if(oldFlask.getCollisionRectangle() != null)
            return true;
        else
            return false;
    }

    public boolean oldCircleTrue(){
        if(oldFlask.getCollisionCircle() != null)
            return true;
        else
            return false;
    }

    public boolean oldCircleArrayTrue(){
        if(oldFlask.getCollisionCircles() != null)
            return true;
        else
            return false;
    }

    public boolean oldPlayerCollision(Player player){
        if(oldFlask.PlayerCollision(player))
            return true;
        else
            return false;
    }

    public int oldId(){
        return oldFlask.getId();
    }

    public void oldLookTime(){
        oldFlask.lookTimer();
    }

    /*
    *       GETTERS
    *       ==AND==
    *       SETTERS
    * */

    public Texture getNowAfterTexture() {
        return nowAfterTexture;
    }

    public Texture getNowFlaskTexture() {
        return nowFlaskTexture;
    }

    public Texture getOldAfterTexture() {
        return oldFlask.getTextureAfter();
    }

    public Texture getOldFlaskTexture() {
        return oldFlaskTexture;
    }

    public FlaskType getFlaskColorType(){
        return flaskSelection.getFlasktype();
    }

    public FlaskType getOldFlaskColorType(){
        return oldFlask.getFlasktype();
    }

    public void onGroundCollision(){
        flaskSelection.onCollisionGround(position, id);
    }

    public boolean getNewFlask(){
        return newFlask;
    }

    public void setNewFlask(Boolean type){
        newFlask = type;
    }

    public Circle getCollisionCircleFlask() {
        return collisionCircleFlask;
    }

    public Rectangle getOldRectangle(){
        return oldFlask.getCollisionRectangle();
    }

    public Circle getOldCircle(){
        return oldFlask.getCollisionCircle();
    }
    public Circle[] getOldCircles(){
        return oldFlask.getCollisionCircles();
    }

    public int getId(){
        return id;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean oldFlaskIsYellow(){
        try {
            return oldFlask.getFlasktype() == FlaskType.YELLOW;
        }catch (Exception e){
            return false;
        }
    }
}
