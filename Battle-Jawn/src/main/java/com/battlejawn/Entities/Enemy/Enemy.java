package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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
    private int strength;
    @Column
    private LocalDateTime createdAt;

    public Enemy(String name, int health, int maxHealth, int potions, int strength, LocalDateTime createdAt) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.potions = potions;
        this.strength = strength;
        this.createdAt = createdAt;
    }

    public Enemy() {
    }

    public void takeDamage(int damage) {

        if (damage < health) {
            health = health - damage;
        } else {
            health = 0;
        }
    }
    
}
