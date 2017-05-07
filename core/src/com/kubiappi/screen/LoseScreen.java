package com.kubiappi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kubiappi.game.GameMain;
import com.kubiappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-04-30.
 */
public class LoseScreen extends AbstractScreen {

    private int score;
    private Music backgroundMusic;
    private Sound uiSound;

    public LoseScreen(GameMain game, int score) {
        super(game);

        stage.addActor(new Image(new Texture("lose.png")));
        uiSound = Gdx.audio.newSound(Gdx.files.internal("UI_Quirky19.mp3"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Jump Up.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        this.game = game;
        this.score = score;
        createHomeButton();
        createAgainButton();
        createLabelScore();
    }

    @Override
    public void drawDebug() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        stage.draw();
        batch.end();
    }

    private void createHomeButton(){
        BitmapFont font = new BitmapFont();
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = font;
        TextButton homeButton = new TextButton("", buttonStyle);
        homeButton.setSize(GameInfo.WIDTH/4,GameInfo.HEIGHT/4);
        homeButton.setPosition(GameInfo.WIDTH/2 - 225,GameInfo.HEIGHT/4);
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                uiSound.play();
                backgroundMusic.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });
        stage.addActor(homeButton);
    }

    private void createAgainButton(){
        BitmapFont font = new BitmapFont();
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = font;
        TextButton againButton = new TextButton("", buttonStyle);
        againButton.setSize(GameInfo.WIDTH/4,GameInfo.HEIGHT/4);
        againButton.setPosition(GameInfo.WIDTH/2 + 25,GameInfo.HEIGHT/4);
        againButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                uiSound.play();
                backgroundMusic.dispose();
                game.setScreen(new MainGameScreen(game));
            }
        });
        stage.addActor(againButton);
    }

    private void createLabelScore(){
        BitmapFont font = new BitmapFont();
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;
        Label scoreText = new Label("Score " + score,labelStyle);
        scoreText.setPosition(GameInfo.WIDTH/2 - 25,GameInfo.HEIGHT/2 + 100);
        scoreText.setColor(Color.WHITE);
        stage.addActor(scoreText);
    }
}
