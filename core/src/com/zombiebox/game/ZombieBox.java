package com.zombiebox.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;
import sun.applet.Main;

import java.util.ArrayList;


public class ZombieBox extends Game implements Screen {

   /* static float WORLD_WIDTH = 1000f;
    static float WORLD_HEIGHT = 1000f;
    private final float CAM_SIZE = 300f;

    private float delta;

    private OrthographicCamera m_cam;

	private SpriteBatch m_batch;
    private Sprite m_mapSprite;

    private BulletManager bulletManager;
    private EnemyManager enemyManager;
    private PlayerManager playerManager;

    private float m_basicSpawnTimer;
    private float m_bigSpawnTimer;
    private float m_shootingSpawnTimer;*/

    private MainMenuScreen m_mainMenuScreen;
    private ZombieBox m_zombieBox;
    private GameScreen m_gameScreen;

    private Game game;

	@Override
	public void create() {
        //delta = 100f;
        m_zombieBox = new ZombieBox();
        game = this;
        game.setScreen(new MainMenuScreen(game));
        //setMainMenuScreen();
    }

    public void createGame() {
        /*m_mapSprite = new Sprite(new Texture("space.jpg"));
        m_mapSprite.setPosition(0f, 0f);
        m_mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        m_cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        m_batch = new SpriteBatch();

        m_basicSpawnTimer = 1f;
        m_bigSpawnTimer = 5f;
        m_shootingSpawnTimer = 10f;

        bulletManager = new BulletManager(m_mapSprite);
        enemyManager = new EnemyManager(m_mapSprite);
        playerManager = new PlayerManager(m_cam);*/
    }

    @Override
    public void resize(int width, int height) {
      /*m_cam.viewportWidth = CAM_SIZE;
        m_cam.viewportHeight = CAM_SIZE * height/width;
        m_cam.update();*/
    }

	@Override
	public void render(float delta) {
        super.render();
	}

    public void renderGame() {
        /*playerManager.update(bulletManager);
        bulletManager.update();
        enemyManager.update(bulletManager, playerManager);

        m_basicSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_bigSpawnTimer -= Gdx.graphics.getDeltaTime();
        m_shootingSpawnTimer -= Gdx.graphics.getDeltaTime();

        if (m_basicSpawnTimer <= 0) {
            float width = MathUtils.random(0,1000f);
            float height = MathUtils.random(0,1000f);
            enemyManager.add(new BasicEnemy(width, height));
            m_basicSpawnTimer = 1f;
        }
        if(m_bigSpawnTimer <= 0) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            enemyManager.add(new BigEnemy(width, height));
            m_bigSpawnTimer = 5f;
        }
        if(m_shootingSpawnTimer <= 0) {
            float width = MathUtils.random(0, 1000f);
            float height = MathUtils.random(0, 1000f);
            enemyManager.add(new ShootingEnemy(width, height));
            m_shootingSpawnTimer = 10f;
        }

        m_batch.setProjectionMatrix(m_cam.combined);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        m_batch.begin();
        m_mapSprite.draw(m_batch);
        bulletManager.render(m_batch);
        enemyManager.render(m_batch);
        playerManager.render(m_batch);
        m_batch.end();*/
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
    public void setMainMenuScreen() {
        m_mainMenuScreen = new MainMenuScreen(game);
        setScreen(m_mainMenuScreen);
    }

    public void setGameScreen() {
        m_gameScreen = new GameScreen(game);
        setScreen(m_gameScreen);
    }
}
