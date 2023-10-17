package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Enemy {

    private String name;
    private int health;
    private int maxHealth;
    private int potions;
    private int strength;
    private LocalDateTime createdAt;

    public Enemy(String name, int health, int maxHealth, int potions, int strength, LocalDateTime createdAt) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.potions = potions;
        this.strength = strength;
        this.createdAt = createdAt;
    }
}
