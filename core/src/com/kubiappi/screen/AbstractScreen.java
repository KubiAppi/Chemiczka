package com.kubiappi.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kubiappi.game.GameMain;
import com.kubiappi.info.GameInfo;

/**
 * Created by Kuba Szczepaniak on 2017-03-15.
 */
public abstract class AbstractScreen implements Screen {

    protected GameMain game;
    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    private StretchViewport viewport;
    protected ShapeRenderer renderer;
    protected Stage stage;

    public AbstractScreen(GameMain game){
        this.game = game;
        batch = new SpriteBatch();
        createCamera();
        viewport = new StretchViewport(GameInfo.WIDTH,GameInfo.HEIGHT);
        stage = new Stage(viewport);
        renderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(stage);
    }

    public abstract void drawDebug();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        viewport.apply();
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

    public void clearScreen(){
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void createCamera(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameInfo.WIDTH,GameInfo.HEIGHT);
        camera.update();
    }
}
