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

    public void updateRocket(Player player) {
        float RocketAngle = getRotation() - 90f;
        double RocketRad = RocketAngle * (Math.PI / 180);
        double velocityX = Math.cos(RocketRad) * m_speed;
        double velocityY = Math.sin(RocketRad) * m_speed;
        translate((float) velocityX * Gdx.graphics.getDeltaTime(),
                (float) velocityY * Gdx.graphics.getDeltaTime());

        if(getDistance(player) < 100f) {
            rotateTowards(player);
            moveTowards(player);
        }
    }

    public void setSpeed(float speed) {
        m_speed = speed;
    }

    public float getDistance(Player player) {
        float x = (getX() - player.getX()) * (getX() - player.getX());
        float y = (getY() - player.getY()) * (getY() - player.getY());

        return (float) Math.sqrt(x + y);
    }

    public void rotateTowards(Player player) {
        float angle = (float) Math.atan2(getY() - player.getY(),
                getX() - player.getX());
        float degrees = (float) (angle * (180 / Math.PI));
        setRotation(degrees - 90);
    }

    public void moveTowards(Player player) {
        float directionX = getX() - player.getX();
        float directionY = getY() - player.getY();
        double sq = Math
                .sqrt(directionX * directionX + directionY * directionY);

        float velocityX = (float) (directionX
                * (m_speed * Gdx.graphics.getDeltaTime()) / sq);

        float velocityY = (float) (directionY * (m_speed * Gdx.graphics.getDeltaTime()) / sq);

        translateX(-velocityX);
        translateY(-velocityY);
    }
}
