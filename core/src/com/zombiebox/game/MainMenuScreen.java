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
 * Created by Vince on 2016-05-18.
 */
public class MainMenuScreen extends Game implements Screen {
    final ZombieBox m_box;
    private OrthographicCamera m_cam;
    private int height;
    private int width;
    private SpriteBatch m_batch;
    private BitmapFont font;
    private Game m_game;

    public MainMenuScreen(Game game) {
        m_game = game;
        m_box = new ZombieBox();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        m_cam = new OrthographicCamera(width, height);
        m_batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/raleway/raleway.fnt"));
    }

    public void create() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((247f/255f), (5f/255f), (5f/255f), 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //m_cam.update();
        m_batch.setProjectionMatrix(m_cam.combined);

        m_batch.begin();
        font.draw(m_batch, "Zombie Box! ", m_cam.position.x - (m_cam.viewportWidth/10f), m_cam.position.y - (m_cam.viewportHeight/100f));
        font.draw(m_batch, "Left-click anywhere to begin!", m_cam.position.x - (m_cam.viewportWidth/5f), m_cam.position.y - (m_cam.viewportHeight/12f));
        m_batch.end();

        if(Gdx.input.isButtonPressed(Input.Keys.LEFT)) {
            m_game.setScreen(new GameScreen(m_game));
            //m_box.setGameScreen();
            //dispose();
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
        /*Gdx.gl.glClearColor((247f / 255f), (5 / 255f), (5f / 255f), 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //m_cam.update();
        m_batch.setProjectionMatrix(m_cam.combined);

        m_batch.begin();
        font.draw(m_batch, "Zombie Box ", 100, 150);
        font.draw(m_batch, "Left-click anywhere to begin!", 100, 100);
        m_batch.end();

        if(Gdx.input.isButtonPressed(Input.Keys.LEFT)) {
            m_box.setGameScreen();
            dispose();

        }*/


    }

    public void pause() {

    }

    public void resume() {

    }

    public void resize(int height, int width) {

    }
}
