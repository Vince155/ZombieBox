package com.zombiebox.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Vince on 2016-05-27.
 */
public class EnemyBulletManager {
    private ArrayList<EnemyBullet> bulletList;
    private Sprite m_world;

    public EnemyBulletManager(Sprite world) {
        bulletList = new ArrayList<>();
        m_world = world;
    }

    public void add(EnemyBullet enemyBullet) {
        bulletList.add(enemyBullet);
    }

    public void update() {
        for (int i = 0; i < bulletList.size(); ++i) {
            bulletList.get(i).updateBullet();
            if (!m_world.getBoundingRectangle().overlaps(bulletList.get(i).getBoundingRectangle())) {
                bulletList.remove(i);
                --i;
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (EnemyBullet b : bulletList) {
            b.draw(batch);
        }
    }

    public ArrayList<EnemyBullet> getBulletList() {
        return bulletList;
    }

}
