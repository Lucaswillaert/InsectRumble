package com.mygdx.game.Insects;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Insect extends Actor {
    private static int health;
    private static int speed;
    private int attackPower;

    public Insect(int health, int speed, int attackPower) {
        this.health = health;
        this.speed = speed;
        this.attackPower = attackPower;
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
}
