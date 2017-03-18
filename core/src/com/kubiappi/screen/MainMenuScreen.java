package com.kubiappi.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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

    public MainMenuScreen(GameMain game) {
        super(game);

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
        batch.end();
    }

    public void createButtonPlay(){
        BitmapFont font = new BitmapFont();
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        buttonPlay = new TextButton("PLAY",textButtonStyle);
        buttonPlay.setDebug(true);
        buttonPlay.setSize(GameInfo.WIDTH/2,GameInfo.HEIGHT/4);
        buttonPlay.setPosition(GameInfo.WIDTH/4,GameInfo.HEIGHT/2);
        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainGameScreen(game));
            }
        });
        stage.addActor(buttonPlay);
    }

    private void createButtonOptions() {
        BitmapFont font = new BitmapFont();
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        buttonPlay = new TextButton("OPTIONS",textButtonStyle);
        buttonPlay.setDebug(true);
        buttonPlay.setSize(GameInfo.WIDTH/2,GameInfo.HEIGHT/4);
        buttonPlay.setPosition(GameInfo.WIDTH/4,GameInfo.HEIGHT/4);
        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //game.setScreen(new MainGameScreen(game));
            }
        });
        stage.addActor(buttonPlay);
    }

}
