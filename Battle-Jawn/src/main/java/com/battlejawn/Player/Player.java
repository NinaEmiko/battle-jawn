package com.battlejawn.Player;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.battlejawn.StatusAilments.StatusAilments;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type")
public class Player {

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