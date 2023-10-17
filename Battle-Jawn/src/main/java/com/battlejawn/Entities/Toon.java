package com.battlejawn.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="toon")
public class Toon {

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
    
}
