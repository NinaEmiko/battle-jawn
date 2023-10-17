package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("THIEF")
public class Thief extends Enemy {

    public Thief(String name, int health, int maxHealth, int potions, int strength, LocalDateTime createdAt) {
        super("Thief", 90, 90, 4, 17, LocalDateTime.now());
    }

}
