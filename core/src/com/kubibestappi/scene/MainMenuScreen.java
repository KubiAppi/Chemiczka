package com.kubibestappi.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kubibestappi.game.GameMain;
import com.kubibestappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-02-27.
 */
public class MainMenuScreen extends MainScreen {

    Texture img;
    int x = 225;

    public MainMenuScreen (GameMain main){
        super(main);
    }

    @Override
    public void show() {
        img = new Texture("PlayCH.png");
    }



    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        if(Gdx.input.getX() < x + img.getWidth() && Gdx.input.getX() > x && GameInfo.HEIGHT - Gdx.input.getY() < 225 +img.getHeight() && GameInfo.HEIGHT - Gdx.input.getY() > 225){
            batch.draw(img, x, 225);
            if(Gdx.input.isTouched()){
                game.setScreen(new MainGameScreen(game));
            }
        }else {
            batch.draw(img, x, 225);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
