package com.zombiebox.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Vince on 2016-06-15.
 */
public class LevelTwoScreen extends Game implements Screen {
    static float WORLD_WIDTH = 1000f;
    static float WORLD_HEIGHT = 1000f;
    private final float CAM_SIZE = 300f;

    private float delta;

    private OrthographicCamera m_cam;

    private SpriteBatch m_batch;
    private Sprite m_mapSprite;
    private BitmapFont font;

    private BulletManager bulletManager;
    private EnemyManager enemyManager;
    private PlayerManager playerManager;
    private EnemyBulletManager enemyBulletManager;

    private float m_basicSpawnTimer;
    private float m_bigSpawnTimer;
    private float m_shootingSpawnTimer;
    private float m_infectedSpawnTimer;

    private float m_levelTimer;

    private Game m_game;

    public LevelTwoScreen(Game game) {
        m_batch = new SpriteBatch();
        m_mapSprite = new Sprite(new Texture("space.jpg"));
        m_cam = new OrthographicCamera();
        font = new BitmapFont();

        bulletManager = new BulletManager(m_mapSprite);
        enemyBulletManager = new EnemyBulletManager(m_mapSprite);
        playerManager = new PlayerManager(m_cam);
        enemyManager = new EnemyManager(m_mapSprite);

        m_basicSpawnTimer = 3f;
        m_bigSpawnTimer = 8f;
        m_shootingSpawnTimer = 12f;
        m_infectedSpawnTimer = 7f;

        m_levelTimer = 125f;

        m_game = game;

        playerManager.getActivePlayer().resetHealth();
        playerManager.getActivePlayer().addLife();
    }

    public void create() {

    }

    @Override
    public void render(float delta) {
        playerManager.update(bulletManager);
        bulletManager.update();
        enemyBulletManager.update();
        enemyManager.update(bulletManager, playerManager, enemyBulletManager);

        m_basicSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_bigSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_shootingSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_infectedSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_levelTimer -= Gdx.graphics.getDeltaTime();

        if (m_basicSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0,1000f);
            enemyManager.add(new BasicEnemy(width, height));
            m_basicSpawnTimer = 3f;
        }
        if(m_bigSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            enemyManager.add(new BigEnemy(width, height));
            m_bigSpawnTimer = 5f;
        }
        if(m_shootingSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            enemyManager.add(new ShootingEnemy(width, height));
            m_shootingSpawnTimer = 12f;
        }
        if(m_infectedSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            enemyManager.add(new InfectedEnemy(width,height));
            m_infectedSpawnTimer = 7f;
        }

        if(playerManager.gameOver()) {
            setScreen(new GameOver(m_game));
        }

        if(m_levelTimer <= 5f) {
            font.draw(m_batch, "Level Two Completed!", m_cam.position.x - (m_cam.viewportWidth / 10f), m_cam.position.y - (m_cam.viewportHeight / 100f));
        }

        if(m_levelTimer <= 0f) {
            setScreen(new LevelThreeScreen(m_game));
        }

        m_batch.setProjectionMatrix(m_cam.combined);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        m_batch.begin();
        m_mapSprite.draw(m_batch);
        bulletManager.render(m_batch);
        enemyBulletManager.render(m_batch);
        enemyManager.render(m_batch);
        playerManager.render(m_batch);
        m_batch.end();
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
