package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import com.battlejawn.Entities.Inventory;
import com.battlejawn.Entities.UserAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

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
    private String role;
    @Column
    private int runCount;
    @Column
    private int winCount;
    @Column
    private int lossCount;
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;
    @Column
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private int level;
    @Column
    private Long experience;
    @Column
    private Long coins;
    @JoinColumn(name = "inventory_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Inventory inventory;

    public Hero() {
    }

    public Hero(String name, int health, int maxHealth, int potions, int maxPotions, String role, int runCount, int winCount, int lossCount, LocalDateTime createdAt) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.potions = potions;
        this.maxPotions = maxPotions;
        this.role = role;
        this.runCount = runCount;
        this.winCount = winCount;
        this.lossCount = lossCount;
        this.createdAt = createdAt;
        this.level = 1;
        this.experience = 0L;
        this.coins = 3L;
        this.inventory = new Inventory();
    }
    
}
