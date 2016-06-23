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

    public void remove(Enemy enemy) { enemyArrayList.remove(enemy); }

    public void update(BulletManager bulletManager, PlayerManager playerManager, EnemyBulletManager enemyBulletManager) {
        m_healthTimer -= Gdx.graphics.getDeltaTime();

        for (int i = 0; i < enemyArrayList.size(); ++i) {
            enemyArrayList.get(i).updateEnemy(enemyBulletManager);
            enemyArrayList.get(i).moveTowards(enemyArrayList.get(i).getSpeed(), playerManager.getActivePlayer());
            enemyArrayList.get(i).rotateTowards(playerManager.getActivePlayer());

            //for an enemy leaving the game space
            if (!m_world.getBoundingRectangle().overlaps(enemyArrayList.get(i).getBoundingRectangle())) {
                enemyArrayList.remove(i);
                --i;
                break;
            }

            //player getting shot
            for(int j = 0; j < enemyBulletManager.getBulletList().size(); ++j) {
                if(enemyBulletManager.getBulletList().get(j).getBoundingRectangle().overlaps(playerManager.getActivePlayer().getBoundingRectangle())) {
                    playerManager.getActivePlayer().isHit();
                    Sound beenShot = Gdx.audio.newSound(Gdx.files.internal("beenshot.wav"));
                    beenShot.play(0.05f);
                }
            }

            //handling enemy and player contact
            if (enemyArrayList.get(i).getBoundingRectangle().overlaps(playerManager.getActivePlayer().getBoundingRectangle()) && m_healthTimer <= 0.0) {
                playerManager.getActivePlayer().isHit();
                m_healthTimer = 3.0;
            }

            //code for handling player death
            if(playerManager.getActivePlayer().isDead()) {
                playerManager.subtractScore();
                playerManager.healthReset();
                playerManager.getActivePlayer().subtractLife();
            }

            //code for damaging and killing enemies
            for (int j = 0; j < bulletManager.getBulletList().size(); ++j) {
                //handling rockets
                for(int k = 0; j < enemyBulletManager.getRocketList().size(); ++k) {
                    //player getting hit by an explosion
                    for(int l = 0; j < enemyBulletManager.getExplosionList().size(); ++l) {
                        if(enemyBulletManager.getExplosionList().get(j).getBoundingRectangle().overlaps(playerManager.getActivePlayer().getBoundingRectangle())) {
                            playerManager.getActivePlayer().setHealth(playerManager.getActivePlayer().getHealth() - 3);
                            Sound explosion = Gdx.audio.newSound(Gdx.files.internal(""));
                            explosion.play(0.05f);
                        }
                        if (bulletManager.getBulletList().get(j).getBoundingRectangle().overlaps(enemyBulletManager.getRocketList().get(k).getBoundingRectangle())) {
                            enemyBulletManager.getRocketList().remove(k);
                            enemyBulletManager.getExplosionList().add(l, new Explosion());
                        }
                    }

                }

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
