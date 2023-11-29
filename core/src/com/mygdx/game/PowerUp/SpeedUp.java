package com.mygdx.game.PowerUp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.math.Rectangle;

public class SpeedUp extends Actor {



    private Texture texture;


    public SpeedUp(){
        texture = new Texture("speedup.png");
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void update(float delta) {
        //toekomstige toevoegingen van animaties
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }
    public Rectangle getBounds(){
        return new Rectangle((int) getX(), (int) getY(),texture.getWidth(),texture.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
