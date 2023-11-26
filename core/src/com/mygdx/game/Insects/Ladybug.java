package com.mygdx.game.Insects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ladybug extends Insect {
    private Texture ladybugTexture;


    public Ladybug(int initialHealth, int initialSpeed, int initialAttackPower) {
        super(initialHealth, initialSpeed, initialAttackPower);
    }

    //inladen van de texturen
    //ladybugTexture = new Texture("ladybug.png");

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Tekenen van de ladybug afbeelding op de positie van de actor
        batch.draw(ladybugTexture, getX(), getY(), getWidth(), getHeight());
    }

}
