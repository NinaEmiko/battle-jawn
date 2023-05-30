package main.java.com.battlejawn.Battle.Jawn.Player;

import java.lang.annotation.Inherited;

import javax.annotation.processing.Generated;

public class Player {
    private Long id;
    private int health;
    private int maxHealth;
    private int strength;
    private int potions;
    private int maxPotions;
    private StatusAilments statusAilments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getMaxPotions() {
        return maxPotions;
    }

    public void setMaxPotions(int maxPotions) {
        this.maxPotions = maxPotions;
    }

    public StatusAilments getStatusAilments() {
        return statusAilments;
    }

    public void setStatusAilments(StatusAilments statusAilments) {
        this.statusAilments = statusAilments;
    }

    public Player(int health, int maxHealth, int strength, int potions, int maxPotions,
            StatusAilments statusAilments) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.potions = potions;
        this.maxPotions = maxPotions;
        this.statusAilments = statusAilments;
    }
}