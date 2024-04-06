package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("WOLF")
public class Wolf extends Enemy {

    public Wolf(int level) {
        super("Wolf", 50, 50, 0, 0, 10, LocalDateTime.now(), level);
    }

}
