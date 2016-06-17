package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Vince on 2015-09-03.
 */
public class Player extends Sprite {

    private float m_speed;
    private double m_bulletTimer;
    private double m_bulletConstant;
    private OrthographicCamera m_cam;
    private float health;

    private int lives;
    //private final float CAM_SIZE = 300f;

    public Player(OrthographicCamera cam) {
        super(new Texture("player.png"));
        m_speed = 100f;
        m_bulletTimer = 0;
        health = 10f;

        setPosition(0f, 0f);

        lives = 2;

        m_cam = cam;
        m_cam.position.set(getX(), getY(), 0);
        m_cam.update();
    }

    public void update(BulletManager bulletManager) {
        m_bulletConstant = 10 * Gdx.graphics.getDeltaTime();
        m_bulletTimer = m_bulletTimer - m_bulletConstant;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            translateY(m_speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            translateX(-m_speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            translateX(m_speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            translateY(-m_speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isButtonPressed(Input.Keys.LEFT) && m_bulletTimer <= 0.0) {
            Bullet b = new Bullet(getX() + (getWidth() / 2f), getY(), getRotation());
            m_bulletTimer = 1f;
            bulletManager.add(b);
            Sound basicGunShot = Gdx.audio.newSound(Gdx.files.internal("gunshot.wav"));
            basicGunShot.play(0.01f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        camUpdate();

    }

    public void isHit() {
        health--;
    }

    public boolean isDead() {
        return (health <= 0);
    }

    public void subtractLife() {
        lives--;
    }

    public int getLives() {
        return lives;
    }

    public void addLife() { lives++; }

    public void addHealth() { health++; }

    public void setHealth(float f) {
        health = f;
    }

    public void resetHealth() {
        health = 10f;
    }

    private void camUpdate() {
        Vector3 mouseInWorld = new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0f);
        mouseInWorld = m_cam.unproject(mouseInWorld);
        float angle = (float) Math.atan2(getY() - mouseInWorld.y,
                getX() - mouseInWorld.x);
        float degrees = (float) (angle * (180 / Math.PI));
        setRotation(degrees - 90);
        m_cam.position.x = getX();
        m_cam.position.y = getY();

        m_cam.update();
    }

    public void render(SpriteBatch batch) {
        draw(batch);
    }

    public float getHealth() { return health; }

}
