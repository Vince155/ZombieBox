package com.zombiebox.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Vince on 2015-08-29.
 */
public class EnemyManager{

    private ArrayList<Enemy> enemyArrayList;
    private float speed;
    private Sprite m_world;
    private double m_healthTimer;

    public EnemyManager(Sprite world) {
        enemyArrayList = new ArrayList();
        m_world = world;
        m_healthTimer = 0.0;
    }

    public void add(Enemy enemy) {
        enemyArrayList.add(enemy);
    }

    public void update(BulletManager bulletManager, PlayerManager playerManager, EnemyBulletManager enemyBulletManager) {
        m_healthTimer -= Gdx.graphics.getDeltaTime();

        for (int i = 0; i < enemyArrayList.size(); ++i) {
            enemyArrayList.get(i).updateEnemy();
            enemyArrayList.get(i).moveTowards(enemyArrayList.get(i).getSpeed(), playerManager.getActivePlayer());
            enemyArrayList.get(i).rotateTowards(playerManager.getActivePlayer());
            if (!m_world.getBoundingRectangle().overlaps(enemyArrayList.get(i).getBoundingRectangle())) {
                enemyArrayList.remove(i);
                --i;
                break;
            }
            for(int j = 0; j < enemyBulletManager.getBulletList().size(); ++j) {
                if(enemyBulletManager.getBulletList().get(j).getBoundingRectangle().overlaps(playerManager.getActivePlayer().getBoundingRectangle())) {
                    playerManager.getActivePlayer().isHit();
                    Sound beenShot = Gdx.audio.newSound(Gdx.files.internal("beenshot.wav"));
                    beenShot.play(0.05f);
                }
            }
            if (enemyArrayList.get(i).getBoundingRectangle().overlaps(playerManager.getActivePlayer().getBoundingRectangle()) && m_healthTimer <= 0.0) {
                playerManager.getActivePlayer().isHit();
                m_healthTimer = 3.0;
            }
            if(playerManager.getActivePlayer().isDead()) {
                playerManager.subtractScore();
                playerManager.healthReset();
                playerManager.getActivePlayer().subtractLife();
            }
            for (int j = 0; j < bulletManager.getBulletList().size(); ++j) {
                if (bulletManager.getBulletList().get(j).getBoundingRectangle().overlaps(enemyArrayList.get(i).getBoundingRectangle())) {
                    enemyArrayList.get(i).isHit();
                    playerManager.updateScore();
                    Sound beenShot = Gdx.audio.newSound(Gdx.files.internal("beenshot.wav"));
                    beenShot.play(0.05f);
                }
                if (enemyArrayList.get(i).isDead()) {
                    enemyArrayList.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Enemy enemy : enemyArrayList){
            enemy.draw(batch);
        }
    }
}
