package com.mygdx.game.Insects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Ant extends Insect {
    private Sprite sprite;
    
    public Ant(int health, int speed, int strength) {
        super( health, speed, strength);
        sprite = new Sprite (new Texture("ant.png"));
        setBounds(0 , 0 ,sprite.getWidth(), sprite.getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (sprite != null) {
            sprite.setPosition(getX(), getY());
            sprite.setSize(getWidth(), getHeight());
            sprite.draw(batch);
        }else {
            System.out.println("AntTexture is null");
        }
    }
}
