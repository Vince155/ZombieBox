package com.zombiebox.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Vince on 2016-06-09.
 */
public class HealthBonusManager {
    private ArrayList<HealthBonus> healthList;
    private Sprite m_world;

    public HealthBonusManager(Sprite world) {
        healthList = new ArrayList<>();
        m_world = world;

    }

    public void add(HealthBonus healthBonus) { healthList.add(healthBonus); }

    public void remove(HealthBonus healthBonus) { healthList.remove(healthBonus); }

    public void update(PlayerManager playerManager) {
        for(int i = 0; i < healthList.size(); ++i) {
            if(playerManager.getActivePlayer().getBoundingRectangle().overlaps(healthList.get(i).getBoundingRectangle())) {
                playerManager.getActivePlayer().addHealth();
                healthList.remove(i);
                --i;
                break;
            }

            if(!m_world.getBoundingRectangle().overlaps(healthList.get(i).getBoundingRectangle())) {
                healthList.remove(i);
                --i;
            }
        }
    }

    public void render(SpriteBatch spriteBatch) {
        for(HealthBonus h : healthList) {
            h.draw(spriteBatch);
        }
    }
}
