package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

public class Wolf extends Enemy {

    public Wolf(String name, int health, int maxHealth, int potions, int strength, LocalDateTime createdAt) {
        super("Wolf", 50, 50, 1, 10, LocalDateTime.now());
    }

}
