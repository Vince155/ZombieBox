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
public class LevelFourScreen extends Game implements Screen {
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
    private HealthBonusManager healthBonusManager;

    private float m_basicSpawnTimer;
    private float m_bigSpawnTimer;
    private float m_shootingSpawnTimer;
    private float m_infectedSpawnTimer;
    private float m_lethalShootingSpawnTimer;
    private float m_rocketSpawnTimer;
    private float m_toughenedBasicSpawnTimer;
    private float m_toughenedBigSpawnTimer;

    private float m_healthTimer;

    private float m_levelTimer;

    private Game m_game;

    public LevelFourScreen(Game game) {
        m_batch = new SpriteBatch();
        m_mapSprite = new Sprite(new Texture("space.jpg"));
        m_cam = new OrthographicCamera();
        font = new BitmapFont();

        bulletManager = new BulletManager(m_mapSprite);
        enemyBulletManager = new EnemyBulletManager(m_mapSprite);
        playerManager = new PlayerManager(m_cam);
        enemyManager = new EnemyManager(m_mapSprite);
        healthBonusManager = new HealthBonusManager(m_mapSprite);

        m_basicSpawnTimer = 1f;
        m_bigSpawnTimer = 3f;
        m_shootingSpawnTimer = 5f;
        m_infectedSpawnTimer = 7f;
        m_lethalShootingSpawnTimer = 8f;
        m_rocketSpawnTimer = 10f;
        m_toughenedBasicSpawnTimer = 9f;
        m_toughenedBigSpawnTimer = 12f;

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
        enemyBulletManager.update(playerManager);
        enemyManager.update(bulletManager, playerManager, enemyBulletManager);
        healthBonusManager.update(playerManager);

        m_basicSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_bigSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_shootingSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_infectedSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_lethalShootingSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_rocketSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_toughenedBasicSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_toughenedBigSpawnTimer -= Gdx.graphics.getDeltaTime();

        if (m_basicSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0,1000f);
            float distance = (float) Math.sqrt((playerManager.getActivePlayer().getX() - width) * (playerManager.getActivePlayer().getX() - width) +
                    playerManager.getActivePlayer().getY() - height);
            enemyManager.add(new BasicEnemy(width, height));
            if(distance <= 100f) {
                enemyManager.remove(new BasicEnemy(width, height));
            }
            m_basicSpawnTimer = 1f;
        }
        if(m_bigSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            float distance = (float) Math.sqrt((playerManager.getActivePlayer().getX() - width) * (playerManager.getActivePlayer().getX() - width) +
                    playerManager.getActivePlayer().getY() - height);
            enemyManager.add(new BigEnemy(width, height));
            if(distance <= 100f) {
                enemyManager.remove(new BigEnemy(width, height));
            }
            m_bigSpawnTimer = 3f;
        }
        if(m_shootingSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            float distance = (float) Math.sqrt((playerManager.getActivePlayer().getX() - width) * (playerManager.getActivePlayer().getX() - width) +
                    playerManager.getActivePlayer().getY() - height);
            enemyManager.add(new ShootingEnemy(width, height));
            if(distance <= 100f) {
                enemyManager.remove(new ShootingEnemy(width, height));
            }
            m_shootingSpawnTimer = 5f;
        }
        if(m_infectedSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            float distance = (float) Math.sqrt((playerManager.getActivePlayer().getX() - width) * (playerManager.getActivePlayer().getX() - width) +
                    playerManager.getActivePlayer().getY() - height);
            enemyManager.add(new InfectedEnemy(width,height));
            if(distance <= 100f) {
                enemyManager.remove(new InfectedEnemy(width, height));
            }
            m_infectedSpawnTimer = 7f;
        }
        if(m_lethalShootingSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            float distance = (float) Math.sqrt((playerManager.getActivePlayer().getX() - width) * (playerManager.getActivePlayer().getX() - width) +
                    playerManager.getActivePlayer().getY() - height);
            enemyManager.add(new LethalShootingEnemy(width,height));
            if(distance <= 100f) {
                enemyManager.remove(new LethalShootingEnemy(width, height));
            }
            m_lethalShootingSpawnTimer = 8f;
        }
        if(m_rocketSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            float distance = (float) Math.sqrt((playerManager.getActivePlayer().getX() - width) * (playerManager.getActivePlayer().getX() - width) +
                    playerManager.getActivePlayer().getY() - height);
            enemyManager.add(new RocketEnemy(width,height));
            if(distance <= 100f) {
                enemyManager.remove(new RocketEnemy(width, height));
            }
            m_rocketSpawnTimer = 10f;
        }
        if(m_toughenedBasicSpawnTimer <= 0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            float distance = (float) Math.sqrt((playerManager.getActivePlayer().getX() - width) * (playerManager.getActivePlayer().getX() - width) +
                    playerManager.getActivePlayer().getY() - height);
            enemyManager.add(new ToughenedEnemy(width,height));
            if(distance <= 100f) {
                enemyManager.remove(new ToughenedEnemy(width, height));
            }
            m_toughenedBasicSpawnTimer = 9f;
        }
        if(m_toughenedBigSpawnTimer <=0 && m_levelTimer >= 5f) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            float distance = (float) Math.sqrt((playerManager.getActivePlayer().getX() - width) * (playerManager.getActivePlayer().getX() - width) +
                    playerManager.getActivePlayer().getY() - height);
            enemyManager.add(new ToughenedBigEnemy(width,height));
            if(distance <= 100f) {
                enemyManager.remove(new ToughenedBigEnemy(width, height));
            }
            m_toughenedBigSpawnTimer = 12f;
        }

        if(m_healthTimer <= 0) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            float distance = (float) Math.sqrt((playerManager.getActivePlayer().getX() - width) * (playerManager.getActivePlayer().getX() - width) +
                    playerManager.getActivePlayer().getY() - height);
            healthBonusManager.add(new HealthBonus(width, height));
            if(distance <= 30f) {
                healthBonusManager.remove(new HealthBonus(width, height));
            }
            m_healthTimer = 20f;
        }

        if(playerManager.gameOver()) {
            setScreen(new GameOver(m_game));
        }

        if(m_levelTimer <= 5f) {
            font.draw(m_batch, "Level Four Completed!", m_cam.position.x - (m_cam.viewportWidth / 10f), m_cam.position.y - (m_cam.viewportHeight / 100f));
        }

        if(m_levelTimer <= 0f) {
            setScreen(new LevelFourScreen(m_game));
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
