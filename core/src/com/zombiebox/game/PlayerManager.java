package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Vince on 2015-09-03.
 */
public class PlayerManager {
    private Player m_player;
    private Sprite m_world;

    static float WORLD_WIDTH = 1000f;
    static float WORLD_HEIGHT = 1000f;

    private float width;
    private float height;

    private int m_score;

    private BitmapFont font;
    private OrthographicCamera m_cam;

    public PlayerManager(OrthographicCamera cam) {
        m_player = new Player(cam);
        m_world = new Sprite(new Texture("space.jpg"));

        width = MathUtils.random(0f, 1000f);
        height = MathUtils.random(0f, 1000f);

        m_world.setPosition(0f, 0f);
        m_world.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        m_score = 0;
        font = new BitmapFont(Gdx.files.internal("fonts/raleway/raleway.fnt"));
        m_cam = cam;
        //m_enemyBulletManager = new EnemyBulletManager();
    }

    public void update(BulletManager bulletManager) {
        m_player.update(bulletManager);
    }

    public void updateScore() {
        m_score += 20;
    }

    // TODO
    public void resetScore() {
        m_score = 0;
    }

    public float getScore() {
        return m_score;
    }

    public void subtractScore() {
        m_score -= 1000;
        if(m_score <= 1000) {
            m_score = 0;
        }
    }

    public void healthReset() {
        m_player.resetHealth();
    }


    public void render(SpriteBatch batch) {
        m_player.render(batch);
        font.draw(batch, "Score: " + m_score, m_cam.position.x - (m_cam.viewportWidth / 2f), font.getLineHeight() +
                m_cam.position.y - (m_cam.viewportHeight / 2f));
        font.draw(batch, "Health: " + m_player.getHealth(), m_cam.position.x - (m_cam.viewportWidth / 2f), font.getLineHeight() +
                m_cam.position.y + (m_cam.viewportHeight / 2.5f));
        font.draw(batch, "Lives: " + m_player.getLives(), m_cam.position.x - (m_cam.viewportWidth / 2f), font.getLineHeight() +
                m_cam.position.y + (m_cam.viewportHeight / 3f));
    }

    public Player getActivePlayer() {
        return m_player;
    }

    public boolean gameOver() {
        if(m_player.getLives() == 0) {
            return true;
        }
        return false;
    }
}
