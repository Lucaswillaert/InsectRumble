package com.mygdx.game.Insects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Insect extends Actor  {
    private  int health;
    private int maxHealth ;
    private  int speed = 60 * 2;
    private int strength;
    private Sprite sprite;

    private Color healthBarColor;


    public Insect(int health, int speed, int strength) {
        this.health = health;
        this.maxHealth= 100;
        this.speed = speed;
        this.strength = strength;
        sprite =new Sprite();
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }
    public int getStrength() {
        return strength;
    }


    //hoe de insecten damage kunnen nemen!
    public void takeDamage(int damage){
        this.health -= damage;
        if (health < 0){
            health = 0; //health niet onder 0 kan !
        }
        //GUI.updateHealth(health, maxHealth);
    }
    public void attack(Ant target) {
        int damage = calculateDamage();
        target.takeDamage(damage);
    }

    private int calculateDamage() {
        // Implementeer hier logica voor het berekenen van schade op basis van aanvalssterkte en mogelijk andere factoren
        health -= strength;
        if (health <=0){
        }
       return 0;
    }

    public void increaseHealth(int healthUp){
        this.health += healthUp;
        if (health > 100){
            health = 100; //health niet boven 100
        }
    }
    public void increaseSpeed(int speedUp) {
        this.speed += speedUp;

    }

    public void increaseStrength(int StrengthUp){
        this.strength += StrengthUp;
        if (strength > 100){
            strength = 100; //health niet boven 100
        }
    }

    public void resetSpeed() {
        this.speed = speed;
    }

    public void resetStrength() {
        this.strength = strength;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    public void update(float delta) {
        // Voer hier logica uit die nodig is voor het bijwerken van het insect.
        // Dit kan bijvoorbeeld zijn voor animaties, timers, enzovoort.
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isDead() {
        return health <= 0;
    }
}
