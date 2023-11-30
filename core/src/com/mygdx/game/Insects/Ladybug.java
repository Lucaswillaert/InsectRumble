package com.mygdx.game.Insects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Ladybug extends Insect {
    private Sprite sprite;
    public Ladybug(int health, int speed, int strength) {
        super(health, speed, strength);
        sprite = new Sprite (new Texture("ladybug.png"));
        setBounds(0 , 0 ,sprite.getWidth(), sprite.getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (sprite != null) {

            float rotation = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT )) {
                rotation = -50; // Right
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                rotation = 135; // Left
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                rotation = 40; // Up
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                rotation = -140; // Down
            }
            sprite.setRotation(rotation);

            //positie juist zetten en de grote
            sprite.setPosition(getX(), getY());
            sprite.setSize(getWidth(), getHeight());

            sprite.draw(batch);
        }else {
            System.out.println("ladybugTexture is null");
        }
    }

}
