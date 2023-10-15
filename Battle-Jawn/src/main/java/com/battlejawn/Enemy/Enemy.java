package com.battlejawn.Enemy;

import lombok.Data;

@Data
public class Enemy {

    private String name;
    private int health;
    private int maxHealth;
    private int potions;
    private int strength;

    public Enemy(String name, int health, int maxHealth, int potions, int strength) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.potions = potions;
        this.strength = strength;
    }
}
