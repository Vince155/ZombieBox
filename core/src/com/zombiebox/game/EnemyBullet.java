package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Vince on 2016-05-27.
 */
public class EnemyBullet extends Sprite {
    private float m_speed;

    public EnemyBullet(float xPos, float yPos, float rotation) {
        super(new Texture("bullet.png"));
        setX(xPos);
        setY(yPos);
        setRotation(rotation);
        m_speed = 100f;
    }

    public void updateBullet() {
        float bulletAngle = getRotation() - 90f;
        double bulletRad = bulletAngle * (Math.PI / 180);
        double velocityX = Math.cos(bulletRad) * m_speed;
        double velocityY = Math.sin(bulletRad) * m_speed;
        translate((float) velocityX * Gdx.graphics.getDeltaTime(),
                (float) velocityY * Gdx.graphics.getDeltaTime());
    }
}
