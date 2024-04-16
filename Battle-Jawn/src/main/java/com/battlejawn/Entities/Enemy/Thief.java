package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("THIEF")
public class Thief extends Enemy {

    public Thief(int level, int health, int potions, int maxPotions, int strength) {
        super("Thief", health, health, potions, maxPotions, strength, LocalDateTime.now(), level);
    }


}
