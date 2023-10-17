package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("ORC")
public class Orc extends Enemy {

    public Orc(Long id,String name, int health, int maxHealth, int potions, int strength, LocalDateTime createdAt) {
        super("Orc", 100, 100, 2, 15, LocalDateTime.now());
    }

}
