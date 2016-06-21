package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Vince on 2015-08-29.
 */


public class BasicEnemy extends Enemy {
    private float health;
    private float speed;

    public BasicEnemy(float xPos, float yPos) {
        super(new Texture("enemy.png"));
        setPosition(xPos, yPos);
        health = 5f;
        speed = 50f;
    }

    public void updateEnemy(EnemyBulletManager enemyBulletManager) {

    }

    public void isHit() {
        health--;
    }

    public boolean isDead() {
      return (health <= 0f);
    }

    @Override
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

    @Override
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
