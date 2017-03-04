package com.kubibestappi.scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kubibestappi.game.GameMain;
import com.kubibestappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-03-04.
 */
public abstract class MainScreen implements Screen {

    protected SpriteBatch batch;
    protected Stage stage;
    protected GameMain game;
    private OrthographicCamera camera;

    public MainScreen(GameMain game){
        this.game = game;
        batch = new SpriteBatch();
        createCamera();
        stage = new Stage(new StretchViewport(GameInfo.WIDTH,GameInfo.HEIGHT));
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void clearScreen(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void createCamera(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        camera.update();
    }
}
