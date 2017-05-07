package com.kubiappi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kubiappi.Entites.Flask;
import com.kubiappi.Entites.Ground;
import com.kubiappi.Entites.Player;
import com.kubiappi.Entites.Teacher;
import com.kubiappi.bonuses.*;
import com.kubiappi.enums.FlaskType;
import com.kubiappi.game.GameMain;
import com.kubiappi.info.GameInfo;

import java.util.Random;

public class MainGameScreen extends AbstractScreen {

    private Teacher teacher;
    private Player player;
    private Ground ground;
    private Flask flask;
    private float timer, timerBonusSpeed,timerBonusShield, countRotation, timerAnim, timerAnimTeacher;
    private BitmapFont lives;
    private String livesNum;
    private boolean right, left, speedUp,shieldUp, teacherThrow;
    private int  bonusNum, animSet, animSetTeacher;
    private BonusMain[] bonuses;
    private float score;
    private Texture playerTexture[],teacherTexture[], backgroundTexture, groundTexture, heartTexture, flaskTexture, oldTexture, shieldTexture;
    private Sprite flaskSprite;
    private Vector2 oldPosition, yellowPosition[];
    private Music backgroundMusic;
    private Sound breakSound, explosiveSound;

    public MainGameScreen(GameMain game) {
        super(game);
        breakSound = Gdx.audio.newSound(Gdx.files.internal("Lamp-Switch_On.mp3"));
        explosiveSound = Gdx.audio.newSound(Gdx.files.internal("Explosion1.mp3"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Monsters-in-Bell-Bottoms_Looping.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        bonusNum = 0;
        speedUp = shieldUp = false;
        bonuses = new BonusMain[5];
        playerTexture = new Texture[4];
        teacherTexture = new Texture[4];
        player = new Player();
        animSet = 0;
        animSetTeacher = 0;
        player.setPosition((int) GameInfo.PLAYER_START_X,(int) GameInfo.PLAYER_START_Y);
        playerTexture[0] = new Texture("Ja static.png");
        playerTexture[1] = new Texture("Ja static2.png");
        playerTexture[2] = new Texture("ja static3.png");
        playerTexture[3] = new Texture("ja static4.png");
        teacherTexture[0] = new Texture("mama.png");
        teacherTexture[1] = new Texture("mama2.png");
        teacherTexture[2] = new Texture("mama3.png");
        teacherTexture[3] = new Texture("mama4.png");
        backgroundTexture = new Texture("background_2.png");
        groundTexture = new Texture("ground.png");
        heartTexture = new Texture("heart_without_black.png");
        shieldTexture = new Texture("shield.png");
        yellowPosition = new Vector2[4];
        ground = new Ground();
        teacher = new Teacher();
        flask = new Flask();
        lives = new BitmapFont();
        Gdx.input.setInputProcessor(this);
        //livesNum = "3";
    }

    @Override
    public void drawDebug() {
        Rectangle playerRectangle = player.getCollisionRectanglePlayer();
        Rectangle groundRectangle = ground.getCollisionGroundRectangle();
        Rectangle teacherRectangle = teacher.getCollisionGroundRectangle();
        Circle flaskCircle = flask.getCollisionCircleFlask();
       // renderer.rect(playerRectangle.x,playerRectangle.y,playerRectangle.width,playerRectangle.height);
        renderer.rect(groundRectangle.x,groundRectangle.y,groundRectangle.width,groundRectangle.height);
        renderer.rect(teacherRectangle.x,teacherRectangle.y,teacherRectangle.width,teacherRectangle.height);
        renderer.circle(flaskCircle.x,flaskCircle.y,flaskCircle.radius);
        for(int i = 0; i<5; i++){
            if(bonuses[i] != null && bonuses[i].CircleIsALive()){
                Circle bonusCircle = bonuses[i].getCollisionCircle();
                renderer.circle(bonusCircle.x,bonusCircle.y,bonusCircle.radius);
            }
        }
        if(!flask.oldIsNull()) {
            FlaskType flaskType = flask.getOldFlaskColorType();
            switch (flaskType){
                case RED:
                    if(flask.oldCircleTrue()){
                        Circle flaskRenderCircle = flask.getOldCircle();
                        oldPosition = new Vector2(flaskRenderCircle.x - GameInfo.FLASK_RED_RADIUS,flaskRenderCircle.y - GameInfo.FLASK_RED_RADIUS);
                        renderer.circle(flaskRenderCircle.x,flaskRenderCircle.y,flaskRenderCircle.radius);
                    }
                    break;
                case BLUE:
                case GREEN:
                    if(flask.oldRectangleTrue()){
                        Rectangle flaskRenderRectangle = flask.getOldRectangle();
                        oldPosition = new Vector2(flaskRenderRectangle.x,flaskRenderRectangle.y);
                        renderer.rect(flaskRenderRectangle.x,flaskRenderRectangle.y,flaskRenderRectangle.width,flaskRenderRectangle.height);
                    }
                    break;
                case YELLOW:
                    if (flask.oldCircleArrayTrue()){
                        Circle[] flaskRenderCircles = flask.getOldCircles();
                        for(int i =0; i<4;i++){
                            yellowPosition[i] = new Vector2(flaskRenderCircles[i].x,flaskRenderCircles[i].y);
                            renderer.circle(flaskRenderCircles[i].x,flaskRenderCircles[i].y,flaskRenderCircles[i].radius);
                        }
                    }
            }
            oldTexture = flask.getOldAfterTexture();
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        setFlaskTexture();
        update();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        drawDebug();
        renderer.end();
        batch.begin();
        batch.draw(backgroundTexture,0,0);
        if(left)
            batch.draw(playerTexture[animSet],player.getPosition().x,player.getPosition().y,playerTexture[animSet].getWidth(),playerTexture[animSet].getHeight());
        else if(right)
            batch.draw(playerTexture[animSet],player.getPosition().x + playerTexture[animSet].getWidth(),player.getPosition().y,-playerTexture[animSet].getWidth(),playerTexture[animSet].getHeight());
        else
            batch.draw(playerTexture[0],player.getPosition().x,player.getPosition().y,playerTexture[0].getWidth(),playerTexture[0].getHeight());
        if(!teacherThrow)
            batch.draw(teacherTexture[0],0,5,teacherTexture[0].getWidth(),teacherTexture[0].getHeight());
        else
            batch.draw(teacherTexture[animSetTeacher],0,5,teacherTexture[animSetTeacher].getWidth(),teacherTexture[animSetTeacher].getHeight());
        batch.draw(groundTexture,-5,0,groundTexture.getWidth(),groundTexture.getHeight());
        if(flask.oldFlaskIsYellow()){
            for (int i =0; i<4;i++){
                if(i <=1 ){
                    batch.draw(oldTexture,yellowPosition[i].x-75,yellowPosition[i].y-75);
                }else
                    batch.draw(oldTexture,yellowPosition[i].x-75,yellowPosition[i].y-75,75,75);

            }
        }
        try {
            if(!flask.oldFlaskIsYellow())
                batch.draw(oldTexture, oldPosition.x, oldPosition.y);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        flaskSprite.draw(batch);
        for(int i=1; i <= player.getLives();i++){
            batch.draw(heartTexture,740 - i*60,400);
        }
        for(BonusMain bonus:bonuses){
            try{
                if(bonus.getPosition().y > 35f){
                    Sprite bonusRotation = new Sprite(bonus.getTexture());
                    bonusRotation.setRotation(countRotation);
                    bonusRotation.setPosition(bonus.getPosition().x,bonus.getPosition().y);
                    bonusRotation.draw(batch);
                }else {
                    batch.draw(bonus.getTexture(), bonus.getPosition().x - GameInfo.BONUS_RADIUS, bonus.getPosition().y - GameInfo.BONUS_RADIUS);
                    System.out.println("ues");
                }
            }catch (Exception e){

            }
        }
        if(shieldUp)
            batch.draw(shieldTexture, player.getPosition().x - 25,player.getPosition().y);
        //lives.draw(batch,livesNum,600f,420f);
        batch.end();

    }

    private void setFlaskTexture() {
        flaskSprite = new Sprite(flask.getNowFlaskTexture());
        flaskSprite.setRotation(countRotation);
        flaskSprite.setPosition(flask.getPosition().x,flask.getPosition().y);
    }

    private void update() {
        timerAnim += Gdx.graphics.getDeltaTime();
        timerAnimTeacher += Gdx.graphics.getDeltaTime();
        if(timerAnimTeacher > 0.05 && teacherThrow){
            animSetTeacher++;
            if(animSetTeacher > 3){
                animSetTeacher = 0;
                teacherThrow = false;
            }
            timerAnimTeacher = 0;
        }
        if(timerAnim > 0.1){
            animSet++;
            if(animSet>3)
                animSet = 0;
            timerAnim=0;
        }
        countRotation += 3;
        if(countRotation >= 360)
            countRotation = 0;
        float lastScore = score;
        score = (float) flask.getId();
        if(score%3 == 0 && lastScore != score){
            selectBonus();
        }
        for (int i=0;i<5;i++){
            if (bonuses[i]!=null){
                bonuses[i].timer();
            }
        }
        for(int i=0; i<5;i++){
            if(bonuses[i] != null && bonuses[i].playerCollision(player)){
                switch (bonuses[i].getBonusType()){
                    case HEAL:
                        bonuses[i].deleteCircle();
                        player.incrementLives();
                        break;
                    case SPEED:
                        speedUp = true;
                        bonuses[i].deleteCircle();
                        break;
                    case SHIELD:
                        shieldUp = true;
                        bonuses[i].deleteCircle();
                        break;
                    case PENCIL:
                        bonuses[i].onPlayerCollision(player);
                        break;
                    case BOOTS:
                        bonuses[i].onPlayerCollision(player);
                        break;
                    default:
                        System.out.println("default");
                        break;
                }
            }
        }
        for(int i=0;i<5;i++){
            if(bonuses[i] != null && bonuses[i].teacherCollision(teacher)){
                switch (bonuses[i].getBonusType()){
                    case PENCIL:
                    case BOOTS:
                        for(int j=0; j<2;j++){
                            selectBonusTeacher();
                        }
                }
            }
        }
        if(flask.groundCollision(ground)){
            flask.setNewFlask(true);
            flask.onGroundCollision();
            flask.randomSelectFlaskType();
            flask.setPosition(new Vector2(GameInfo.FLASK_START_X, GameInfo.FLASK_START_Y));
        }
        if(flask.playerCollision(player)){
            breakSound.play();
            if(!shieldUp)
                player.decrementLives();
            flask.setNewFlask(true);
            flask.setPosition(new Vector2(GameInfo.FLASK_START_X, GameInfo.FLASK_START_Y));
        }


        System.out.println(flask.getFlaskColorType());

        if(speedUp){
            timerBonusSpeed += Gdx.graphics.getDeltaTime();
            player.setSpeed(500f);
            if(timerBonusSpeed > 5f){
                speedUp = false;
                timerBonusSpeed = 0;
            }

        }else
            player.setSpeed(200f);

        if(shieldUp){
            timerBonusShield += Gdx.graphics.getDeltaTime();
            if(timerBonusShield > 5f){
                shieldUp = false;
                timerBonusShield = 0;
            }
        }

        if(!shieldUp && !flask.oldIsNull() && (flask.oldCircleTrue()|| flask.oldRectangleTrue() || flask.oldCircleArrayTrue()) && flask.oldPlayerCollision(player) && player.getLastId() != flask.oldId()){
            if(flask.getOldFlaskColorType() == FlaskType.BLUE){
                player.setSpeed(400f);
            }
            else {
                player.setLastId(flask.oldId());
                player.decrementLives();
            }
        }

        livesNum = Integer.toString(player.getLives());
        if(!flask.oldIsNull()){
            flask.oldLookTime();
        }
        if(livesNum.equals("0")) {
            backgroundMusic.dispose();
            game.setScreen(new LoseScreen(game, (int) score));
        }
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


    }

    private void selectBonus() {
        Random rand = new Random();
        switch (rand.nextInt(5)){
            case 0:
                bonuses[bonusNum] = new BonusHeal();
                break;
            case 1:
                bonuses[bonusNum] = new BonusSpeed();
                break;
            case 2:
                bonuses[bonusNum] = new BonusShield();
                break;
            case 3:
                bonuses[bonusNum] = new BonusPencil();
                break;
            case 4:
                bonuses[bonusNum] = new BonusBoots();
                break;
            default:
                bonuses[bonusNum] = new BonusPencil();
                break;
        }
        do {
            bonuses[bonusNum].createCircle(new Vector2(rand.nextInt(600) + 150, GameInfo.BONUS_MAIN_Y));
        }while (Intersector.overlaps(bonuses[bonusNum].getCollisionCircle(),player.getCollisionRectanglePlayer()));
        bonusNum++;
        if(bonusNum > 4)
            bonusNum = 0;
    }

    private void selectBonusTeacher() {
        Random rand = new Random();
        switch (rand.nextInt(3)){
            case 0:
                bonuses[bonusNum] = new BonusHeal();
                break;
            case 1:
                bonuses[bonusNum] = new BonusSpeed();
                break;
            case 2:
                bonuses[bonusNum] = new BonusShield();
                break;
            default:
                bonuses[bonusNum] = new BonusSpeed();
                break;
        }
        bonuses[bonusNum].createCircle(new Vector2(150, 100));
        bonusNum++;
        if(bonusNum > 4)
            bonusNum = 0;
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
        position.x -= player.getSpeed() * Gdx.graphics.getDeltaTime();
        if(position.x < 170)
            position.x = 170;
        player.setPosition((int)position.x,(int)position.y);
    }

    private void goRight(){
        Vector2 position = player.getPosition();
        position.x += player.getSpeed() * Gdx.graphics.getDeltaTime();
        if(position.x > 630)
            position.x = 630;
        player.setPosition((int)position.x,(int)position.y);
    }

    private void flaskTimer(){
        if(flask.getNewFlask()){
            timer = 0;
        }
        timer += Gdx.graphics.getDeltaTime();
        if(timer < 1){
            teacherThrow = true;
            flask.throwFlask();
        }
    }
}
