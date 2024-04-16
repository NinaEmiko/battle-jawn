package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("ORC")
public class Orc extends Enemy {

    public Orc(int level, int health, int potions, int strength) {
        super("Orc", health, health, potions, potions, strength, LocalDateTime.now(), level);
    }

}
