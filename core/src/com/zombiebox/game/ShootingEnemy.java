package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Vince on 2016-04-29.
 */
public class ShootingEnemy extends Enemy {
    private float health;
    private float speed;
    private double m_bulletTimer;
    private double m_bulletConstant;
    private Sprite world;
    private EnemyBulletManager m_enemyBulletManager;

    public ShootingEnemy(float xPos, float yPos) {
        super(new Texture("shootingEnemy.png"));
        setPosition(xPos, yPos);
        health = 3f;
        speed = 40f;
        m_bulletTimer = 1.0;
        m_enemyBulletManager = new EnemyBulletManager(world);
    }

    public void updateEnemy(EnemyBulletManager enemyBulletManager) {
        m_bulletConstant = Gdx.graphics.getDeltaTime() / 2;
        m_bulletTimer = m_bulletTimer - m_bulletConstant;
        if(m_bulletTimer <= 0.0) {
            EnemyBullet b = new EnemyBullet(getX() + (getWidth() / 2f), getY(), getRotation());
            enemyBulletManager.add(b);
            m_bulletTimer = 5;
            Sound basicGunShot = Gdx.audio.newSound(Gdx.files.internal("gunshot.wav"));
            basicGunShot.play(0.01f);
        }
    }

    public void isHit() { health--; }

    public boolean isDead() { return (health <= 0f); }

    public void moveTowards(float speed, Player player) {
        float directionX = getX() - player.getX();
        float directionY = getY() - player.getY();
        double sq = Math
                .sqrt(directionX * directionX + directionY * directionY);

        float velocityX = (float) (directionX
                * (speed * Gdx.graphics.getDeltaTime()) / sq);

        float velocityY = (float) (directionY * (speed * Gdx.graphics.getDeltaTime()) / sq);

        translateX(-velocityX);
        translateY(-velocityY);
    }

    public void rotateTowards(Player player){
        float angle = (float) Math.atan2(getY() - player.getY(),
                getX() - player.getX());
        float degrees = (float) (angle * (180 / Math.PI));
        setRotation(degrees - 90);
    }

    public float getSpeed() { return speed; }
}
