package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Vince on 2016-06-21.
 */
public class RocketEnemy extends Enemy {
    private float health;
    private float m_speed;
    private double m_rocketTimer;
    private double m_rocketConstant;
    private Sprite world;

    public RocketEnemy(float xPos, float yPos) {
        super(new Texture("rocketEnemy.png"));
        health = 10f;
        m_speed = 35f;
        m_rocketTimer = 1;
        m_rocketConstant = Gdx.graphics.getDeltaTime();
    }

    public void updateEnemy(EnemyBulletManager enemyBulletManager) {
        m_rocketTimer -= m_rocketConstant;
        if(m_rocketTimer <= 0.0) {
            Rocket r = new Rocket(getX() + (getWidth() / 2f), getY(), getRotation());
            enemyBulletManager.add(r);
            r.setSpeed(400f);
            m_rocketTimer = 5;
            //Sound rocketLaunch = Gdx.audio.newSound(Gdx.files.internal("gunshot.wav"));
            //rocketLaunch.play(0.01f);
        }
    }

    public void isHit() {
        health--;
    }

    public boolean isDead() {
        return (health <= 0f);
    }

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

    public void rotateTowards(Player player) {
        float angle = (float) Math.atan2(getY() - player.getY(),
                getX() - player.getX());
        float degrees = (float) (angle * (180 / Math.PI));
        setRotation(degrees - 90);
    }

    public float getSpeed() {
        return m_speed;
    }


}
