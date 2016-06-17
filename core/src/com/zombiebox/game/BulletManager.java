package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Vince on 2015-08-28.
 */
public class BulletManager {
    private ArrayList<Bullet> bulletList;
    private Sprite m_world;

    public BulletManager(Sprite world) {
        bulletList = new ArrayList();
        m_world = world;
    }

    public void add(Bullet bullet) {
        bulletList.add(bullet);
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
        for (Bullet b : bulletList) {
            b.draw(batch);
        }
    }

    public ArrayList<Bullet> getBulletList() {
        return bulletList;
    }
}
