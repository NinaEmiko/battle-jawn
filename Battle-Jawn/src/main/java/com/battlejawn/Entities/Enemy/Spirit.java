package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

public class Spirit extends Enemy {

    public Spirit(String name, int health, int maxHealth, int potions, int strength, LocalDateTime createdAt) {
        super("Spirit", 150, 150, 1, 20, LocalDateTime.now());
    }

}
