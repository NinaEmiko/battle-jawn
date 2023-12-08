package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "enemy_type")
public abstract class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private int health;
    @Column
    private int maxHealth;
    @Column
    private int potions;
    @Column
    private int maxPotions;
    @Column
    private int strength;
    @Column
    private LocalDateTime createdAt;

    public Enemy(String name, int health, int maxHealth, int potions, int maxPotions, int strength, LocalDateTime createdAt) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.potions = potions;
        this.maxPotions = maxPotions;
        this.strength = strength;
        this.createdAt = createdAt;
    }

    public Enemy() {
    }
}
