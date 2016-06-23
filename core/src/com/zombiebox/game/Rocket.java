package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Vince on 2016-06-21.
 */
public class Rocket extends Sprite {
    private float m_speed;

    public Rocket(float xPos, float yPos, float rotation) {
        super(new Texture(""));
        setX(xPos);
        setY(yPos);
        setRotation(rotation);
        m_speed = 100f;
    }

    public void updateRocket() {
        float RocketAngle = getRotation() - 90f;
        double RocketRad = RocketAngle * (Math.PI / 180);
        double velocityX = Math.cos(RocketRad) * m_speed;
        double velocityY = Math.sin(RocketRad) * m_speed;
        translate((float) velocityX * Gdx.graphics.getDeltaTime(),
                (float) velocityY * Gdx.graphics.getDeltaTime());
    }

    public void setSpeed(float speed) {
        m_speed = speed;
    }
}
