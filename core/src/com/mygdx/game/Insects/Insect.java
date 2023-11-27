package com.mygdx.game.Insects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Insect extends Actor  {
    private static int health;
    private static int speed = 60 * 2;

    private Sprite sprite;
    private int attackPower;

    public Insect(int health, int speed, int attackPower) {
        this.health = health;
        this.speed = speed;
        this.attackPower = attackPower;
        sprite =new Sprite();
    }




    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackPower() {
        return attackPower;
    }


    //hoe de insecten damage kunnen nemen!
    public void takeDamage(int damage){
        this.health -= damage;
        if (health < 0){
            health = 0; //health niet onder 0 kan !
        }
    }
    public void attack(Ant target) {
        int damage = calculateDamage();
        target.takeDamage(damage);
    }

    private int calculateDamage() {
        // Implementeer hier logica voor het berekenen van schade op basis van aanvalssterkte en mogelijk andere factoren
        return attackPower;
    }

    public void increaseHealth(int healthUp){
        this.health += healthUp;
        if (health > 100){
            health = 100; //health niet boven 100
        }
    }
    public void increaseSpeed(int speedUp) {
        this.speed += speedUp;
        if (speed > 100){
            speed = 100; //speed niet boven 100
        }
    }

    @Override
    public void act(float delta) {
        handleInput();
    }


    private void handleInput() {
        // Gemeenschappelijke invoerlogica voor alle insecten
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveBy(-speed * Gdx.graphics.getDeltaTime(), 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveBy(speed * Gdx.graphics.getDeltaTime(), 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveBy(0, -speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveBy(0, speed * Gdx.graphics.getDeltaTime());
        }
    }


}
