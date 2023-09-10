package com.battlejawn.Entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hero")
public class Hero {

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
    private int strength;
    @Column
    private int potions;
    @Column
    private int maxPotions;
    @Column
    private Role role;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @Column
    private LocalDateTime creationDate;
    @Column
    private int wins;
    @Column
    private int losses;
    @Column
    private int timesRanAway;
    @Column
    private int totalBattles;

    public Hero() {
        this.creationDate = LocalDateTime.now();
        this.wins = 0;
        this.losses = 0;
        this.timesRanAway = 0;
        this.totalBattles = 0;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Hero(String name, int health, int maxHealth, int strength, int potions, int maxPotions, Role role, Account account, LocalDateTime creationDate, int wins, int losses, int timesRanAway, int totalBattles) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.potions = potions;
        this.maxPotions = maxPotions;
        this.role = role;
        this.account = account;
        this.creationDate = creationDate;
        this.wins = wins;
        this.losses = losses;
        this.timesRanAway = timesRanAway;
        this.totalBattles = totalBattles;
    }
}