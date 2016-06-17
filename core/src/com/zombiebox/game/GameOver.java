package com.zombiebox.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Vince on 2016-06-06.
 */
public class GameOver extends Game implements Screen {
    private Game m_game;
    private OrthographicCamera m_cam;
    private int height;
    private int width;
    private SpriteBatch m_batch;
    private BitmapFont font;

    public GameOver(Game game) {
        m_game = game;
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        m_cam = new OrthographicCamera(width, height);
        m_batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/raleway/raleway.fnt"));
    }

    public void create() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor((247f / 255f), (5f / 255f), (5f / 255f), 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        m_batch.setProjectionMatrix(m_cam.combined);

        m_batch.begin();
        font.draw(m_batch, "Game Over!", m_cam.position.x - (m_cam.viewportWidth / 10f), m_cam.position.y - (m_cam.viewportHeight / 100f));
        font.draw(m_batch, "Press The Left Mouse Button to Play Again", m_cam.position.x - (m_cam.viewportWidth / 10f), m_cam.position.y - (m_cam.viewportHeight / 100f));
        m_batch.end();

        if(Gdx.input.isButtonPressed(Input.Keys.LEFT)) {
            setScreen(new GameScreen(m_game));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

    }

    public void dispose() {

    }

    public void hide() {

    }

    public void show() {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void resize(int height, int width) {

    }
}
