package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("SPIRIT")
public class Spirit extends Enemy {

    public Spirit(int level, int health, int strength) {
        super("Spirit", health, health, 0, 0, strength, LocalDateTime.now(), level);
    }

}
