package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Vince on 2016-06-14.
 */
public class ToughenedEnemy extends  Enemy {
    private float speed;
    private float health;
    public ToughenedEnemy(float xPos, float yPos) {
        super(new Texture("toughenedEnemy.png"));
        setPosition(xPos, yPos);
        speed = 50f;
        health = 10f;
    }

    public void updateEnemy() {

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
        return speed;
    }
}
