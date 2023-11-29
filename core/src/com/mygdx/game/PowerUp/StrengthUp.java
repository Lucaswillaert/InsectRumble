package com.mygdx.game.PowerUp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Rectangle;

public class StrengthUp extends Actor {


    private Texture texture;

    public StrengthUp(float x, float y){
        texture = new Texture("strength.png");
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
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

    public void update(float delta) {
        //toekomstige toevoegingen van animaties

    }



}
