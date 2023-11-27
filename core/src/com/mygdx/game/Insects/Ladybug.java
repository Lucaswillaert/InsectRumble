package com.mygdx.game.Insects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ladybug extends Insect {

    private Sprite sprite;


    public Ladybug(int initialHealth, int initialSpeed, int initialAttackPower) {
        super(initialHealth, initialSpeed, initialAttackPower);
        sprite = new Sprite (new Texture("ladybug.png"));
        setBounds(0 , 0 ,sprite.getWidth(), sprite.getHeight());
    }

    //inladen van de texturen
    //ladybugTexture = new Texture("ladybug.png");

    @Override
    public void draw(Batch batch, float parentAlpha) {
      //  super.draw(batch, parentAlpha);

        if (sprite != null) {
            sprite.setPosition(getX(), getY());
            sprite.setSize(getWidth(), getHeight());
            sprite.draw(batch);
        }else {
            System.out.println("ladybugTexture is null");
        }
    }


}
