package com.zombiebox.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Created by Vince on 2016-06-09.
 */
public class HealthBonusManager {
    private ArrayList<HealthBonus> healthList;
    private Sprite m_world;
    private Player m_player;

    public HealthBonusManager(Sprite world) {
        healthList = new ArrayList<>();
        m_world = world;
    }

    public void add(HealthBonus healthBonus) { healthList.add(healthBonus); }

    public void update() {
        for(int i = 0; i < healthList.size(); ++i) {
            if(healthList.get(i).getBoundingRectangle().overlaps(m_player.getBoundingRectangle())) {
                m_player.addHealth();
                healthList.remove(i);
                --i;
            }
        }
    }
}
