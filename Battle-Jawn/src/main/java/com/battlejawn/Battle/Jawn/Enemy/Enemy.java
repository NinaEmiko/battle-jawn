package com.battlejawn.Battle.Jawn.Enemy;

public class Enemy {

    private String name;
    private int health;
    private int maxHealth;
    private int potions;
    private int strength;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Enemy(String name, int health, int maxHealth, int potions, int strength) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.potions = potions;
        this.strength = strength;
    }
}
