package com.zombiebox.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Vince on 2016-06-09.
 */
public class HealthBonus extends Sprite {


    public HealthBonus(float xPos, float yPos) {
        super(new Texture("health.png"));
        setPosition(xPos, yPos);
    }
}
