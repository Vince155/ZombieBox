package com.zombiebox.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Vince on 2015-09-01.
 */
public abstract class Enemy extends Sprite {
    public Enemy(Texture texture) {
        super(texture);
    }
    public abstract void updateEnemy(EnemyBulletManager enemyBulletManager);
    public abstract void isHit();
    public abstract boolean isDead();
    public abstract void moveTowards(float speed, Player player);
    public abstract void rotateTowards(Player player);
    public abstract float getSpeed();
}
