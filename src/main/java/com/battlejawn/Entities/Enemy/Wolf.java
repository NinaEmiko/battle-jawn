package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("WOLF")
public class Wolf extends Enemy {

    public Wolf(int level, int health, int strength) {
        super("Wolf", health, health, 0, 0, strength, LocalDateTime.now(), level);
    }

}
