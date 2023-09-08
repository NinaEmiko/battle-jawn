package com.battlejawn.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// import com.battlejawn.StatusAilments.StatusAilments;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int health;
    private int maxHealth;
    private int strength;
    private int potions;
    private int maxPotions;
    private Role role;
    private Date creationDate;
    private int wins;
    private int losses;
    private int timesRanAway;
    private int totalBattles;
    // private StatusAilments statusAilments;

    public Long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTimesRanAway() {
        return timesRanAway;
    }

    public void setTimesRanAway(int timesRanAway) {
        this.timesRanAway = timesRanAway;
    }

    public int getTotalBattles() {
        return totalBattles;
    }

    public void setTotalBattles(int totalBattles) {
        this.totalBattles = totalBattles;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // public StatusAilments getStatusAilments() {
    //     return statusAilments;
    // }

    // public void setStatusAilments(StatusAilments statusAilments) {
    //     this.statusAilments = statusAilments;
    // }

    public Character(String name, int health, int maxHealth, int strength, int potions, int maxPotions, Role role, Date creationDate, int wins, int losses, int timesRanAway, int totalBattles) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.potions = potions;
        this.maxPotions = maxPotions;
        this.role = role;
        this.creationDate = creationDate;
        this.wins = wins;
        this.losses = losses;
        this.timesRanAway = timesRanAway;
        this.totalBattles = totalBattles;
    }
}