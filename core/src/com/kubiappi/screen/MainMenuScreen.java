package com.kubiappi.screen;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kubiappi.game.GameMain;
import com.kubiappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-03-15.
 */
public class MainMenuScreen extends AbstractScreen {

    private TextButton buttonPlay,buttonOptions;
    private Texture backgroundTexture;
    private Music backgroundMusic;
    private Sound uiSound;
    private Image bgImgae;

    public MainMenuScreen(GameMain game) {
        super(game);

        backgroundTexture = new Texture("menu.png");
        bgImgae = new Image(backgroundTexture);
        stage.addActor(bgImgae);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Cool-Adventure-Intro.mp3"));
        uiSound = Gdx.audio.newSound(Gdx.files.internal("UI_Quirky19.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        createButtonPlay();
        createButtonOptions();
    }



    @Override
    public void drawDebug() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        stage.draw();
        batch.draw(backgroundTexture,0,0);
        batch.end();
    }

    public void createButtonPlay(){
        BitmapFont font = new BitmapFont();
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        buttonPlay = new TextButton("",textButtonStyle);
        buttonPlay.setSize(GameInfo.WIDTH/2,GameInfo.HEIGHT/4);
        buttonPlay.setPosition(GameInfo.WIDTH/4,GameInfo.HEIGHT/2);
        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                uiSound.play();
                backgroundMusic.dispose();
                game.setScreen(new MainGameScreen(game));
            }
        });
        stage.addActor(buttonPlay);
    }

    private void createButtonOptions() {
        BitmapFont font = new BitmapFont();
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        buttonPlay = new TextButton("",textButtonStyle);
        buttonPlay.setSize(GameInfo.WIDTH/2,GameInfo.HEIGHT/4);
        buttonPlay.setPosition(GameInfo.WIDTH/4,GameInfo.HEIGHT/4);
        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //uiSound.play();
                //game.setScreen(new MainGameScreen(game));
            }
        });
        stage.addActor(buttonPlay);
    }

}
