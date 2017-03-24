package com.kubiappi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.Entites.Flask;
import com.kubiappi.Entites.Ground;
import com.kubiappi.Entites.Player;
import com.kubiappi.enums.FlaskType;
import com.kubiappi.game.GameMain;
import com.kubiappi.info.GameInfo;

public class MainGameScreen extends AbstractScreen {

    private Player player;
    private Ground ground;
    private Flask flask;
    private float timer;
    private BitmapFont lives;
    private String livesNum;
    private boolean right, left;

    public MainGameScreen(GameMain game) {
        super(game);
        player = new Player();
        player.setPosition((int) GameInfo.PLAYER_START_X,(int) GameInfo.PLAYER_START_Y);
        ground = new Ground();
        flask = new Flask();
        lives = new BitmapFont();
        Gdx.input.setInputProcessor(this);
        //livesNum = "3";
    }

    @Override
    public void drawDebug() {
        Rectangle playerRectangle = player.getCollisionRectanglePlayer();
        Rectangle groundRectangle = ground.getCollisionGroundRectangle();
        Circle flaskCircle = flask.getCollisionCircleFlask();
        renderer.rect(playerRectangle.x,playerRectangle.y,playerRectangle.width,playerRectangle.height);
        renderer.rect(groundRectangle.x,groundRectangle.y,groundRectangle.width,groundRectangle.height);
        renderer.circle(flaskCircle.x,flaskCircle.y,flaskCircle.radius);
        if(flask.oldFlask != null) {
            FlaskType flaskType = flask.getOldFlaskColorType();
            switch (flaskType){
                case RED:
                    if(flask.oldFlask.getCollisionCircle() != null){
                        Circle flaskRenderCircle = flask.oldFlask.getCollisionCircle();
                        renderer.circle(flaskRenderCircle.x,flaskRenderCircle.y,flaskRenderCircle.radius);
                    }
                    break;
                case GREEN:
                    if(flask.oldFlask.getCollisionRectangle() != null){
                        Rectangle flaskRenderRectangle = flask.oldFlask.getCollisionRectangle();
                        renderer.rect(flaskRenderRectangle.x,flaskRenderRectangle.y,flaskRenderRectangle.width,flaskRenderRectangle.height);
                    }
                    break;
            }
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        drawDebug();
        renderer.end();
        batch.begin();
        lives.draw(batch,livesNum,600f,420f);
        batch.end();

    }

    private void update() {
        livesNum = Integer.toString(player.lives);
        if(flask.oldFlask != null){
            flask.oldFlask.lookTimer();
        }
        if(livesNum.equals("0"))
            System.exit(0);
        flaskTimer();
        flask.update();
//        if(Gdx.input.getX() > GameInfo.WIDTH || Gdx.input.isKeyPressed(Keys.RIGHT)){
//            if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.RIGHT)){
//                goRight();
//            }
//        }
//        if(Gdx.input.getX() < GameInfo.WIDTH  || Gdx.input.isKeyPressed(Keys.LEFT)){
//            if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.LEFT)){
//                goLeft();
//            }
//        }
        if(right)
            goRight();
        if(left)
            goLeft();

        if(flask.groundCollision(ground)){
            flask.setNewFlask(true);
            flask.onGroundCollision();
            flask.randomSelectFlaskType();
            flask.setPosition(new Vector2(GameInfo.FLASK_START_X, GameInfo.FLASK_START_Y));
        }
        if(flask.playerCollision(player)){
            player.lives -= 1;
            flask.setNewFlask(true);
            flask.setPosition(new Vector2(GameInfo.FLASK_START_X, GameInfo.FLASK_START_Y));
        }
        if(flask.oldFlask != null && (flask.oldFlask.getCollisionCircle() != null || flask.oldFlask.getCollisionRectangle() != null) && flask.oldFlask.PlayerCollision(player) && player.getLastId() != flask.oldFlask.getId()){
            player.setLastId(flask.oldFlask.getId());
            player.lives -= 1;
        }
    }

    @Override
    public boolean touchDown(int X, int Y, int pointer, int button) {
        Vector2 position = new Vector2(X,Y);
        if(position.x > GameInfo.WIDTH/2){
            right = true;
            return true;
        }else if(position.x < GameInfo.WIDTH/2){
            left = true;
            return true;
        }

        return super.touchDown(X, Y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        right = false;
        left = false;
        return super.touchUp(screenX, screenY, pointer, button);
    }

    private void goLeft() {
        Vector2 position = player.getPosition();
        position.x -= 200f * Gdx.graphics.getDeltaTime();
        if(position.x < 170)
            position.x = 170;
        player.setPosition((int)position.x,(int)position.y);
    }

    public void goRight(){
        Vector2 position = player.getPosition();
        position.x += 200f * Gdx.graphics.getDeltaTime();
        if(position.x > 630)
            position.x = 630;
        player.setPosition((int)position.x,(int)position.y);
    }

    public void flaskTimer(){
        if(flask.getNewFlask()){
            timer = 0;
        }
        timer += Gdx.graphics.getDeltaTime();
        if(timer < 1){
            flask.throwFlask();
        }
    }
}
