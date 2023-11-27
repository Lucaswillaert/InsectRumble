package com.mygdx.game.PowerUp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.math.Rectangle;

public class SpeedUp extends Actor {


    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    private Texture texture;
    public Texture getTexture() {
        return texture;
    }
    public SpeedUp(){
        texture = new Texture("speedUp.png");
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
    }

    public void update(float delta) {
        // Voeg hier eventuele logica toe die nodig is voor het bijwerken van de StrengthUp power-up.
        // Bijvoorbeeld: animaties, timers, enzovoort.
    }

    public Rectangle getBounds(){
        return new Rectangle((int) getX(), (int) getY(),texture.getWidth(),texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
