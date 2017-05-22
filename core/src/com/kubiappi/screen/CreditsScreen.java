package com.kubiappi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kubiappi.game.GameMain;
import com.kubiappi.info.GameInfo;

public class CreditsScreen extends AbstractScreen {

    private Texture background;
    private Sound uiSound;

    public CreditsScreen(GameMain game) {
        super(game);
        background = new Texture("credits.png");
        Image bgImage = new Image(background);
        stage.addActor(bgImage);
        uiSound = Gdx.audio.newSound(Gdx.files.internal("UI_Quirky19.mp3"));
        createBackButton();
        Gdx.input.setInputProcessor(stage);
    }

    private void createBackButton() {
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        TextButton button = new TextButton("",textButtonStyle);
        button.setSize(GameInfo.WIDTH/8,GameInfo.HEIGHT/8);
        button.setPosition(GameInfo.WIDTH/20,GameInfo.HEIGHT - GameInfo.HEIGHT/6);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                uiSound.play();
                game.setScreen(new MainMenuScreen(game));
            }
        });
        stage.addActor(button);
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
}
