package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

public class Orc extends Enemy {

    public Orc(String name, int health, int maxHealth, int potions, int strength, LocalDateTime createdAt) {
        super("Orc", 100, 100, 2, 15, LocalDateTime.now());
    }

}
