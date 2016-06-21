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
    private Player m_player;
    private OrthographicCamera m_cam;

    public HealthBonusManager(Sprite world) {
        healthList = new ArrayList<>();
        m_world = world;
        m_cam = new OrthographicCamera();
        m_player = new Player(m_cam);
    }

    public void add(HealthBonus healthBonus) { healthList.add(healthBonus); }

    public void update() {
        for(int i = 0; i < healthList.size(); ++i) {
            if(m_player.getBoundingRectangle().overlaps(healthList.get(i).getBoundingRectangle())) {
                m_player.addHealth();
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
