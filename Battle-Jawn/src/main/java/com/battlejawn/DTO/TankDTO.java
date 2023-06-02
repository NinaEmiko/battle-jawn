package com.battlejawn.DTO;

import com.battlejawn.StatusAilments.StatusAilments;

public class TankDTO {
    private int health;
    private int maxHealth;
    private int strength;
    private int potions;
    private int maxPotions;
    private StatusAilments statusAilments;

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

}
