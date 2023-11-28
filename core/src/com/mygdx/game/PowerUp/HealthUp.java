package com.mygdx.game.PowerUp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Rectangle;

public class HealthUp extends Actor {




    private Texture texture;

    public HealthUp() {
        texture = new Texture("Health.png");
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
    }
    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public void update(float delta) {
        // Voeg hier eventuele logica toe die nodig is voor het bijwerken van de StrengthUp power-up.
        // Bijvoorbeeld: animaties, timers, enzovoort.
    }

    public com.badlogic.gdx.math.Rectangle getBounds(){
        return new Rectangle((int) getX(), (int) getY(),texture.getWidth(),texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
