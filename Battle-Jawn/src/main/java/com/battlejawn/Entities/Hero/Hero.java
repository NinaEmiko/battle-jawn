package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="hero")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "hero_type")
public abstract class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int health;
    @Column
    private int maxHealth;
    @Column
    private int potions;
    @Column
    private int maxPotions;
    @Column
    private String role;
    @Column
    private LocalDateTime createdAt;

    public Hero() {

    }

    public Hero(int health, int maxHealth, int potions, int maxPotions, String role, LocalDateTime createdAt) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.potions = potions;
        this.maxPotions = maxPotions;
        this.role = role;
        this.createdAt = createdAt;

    }
    
}
