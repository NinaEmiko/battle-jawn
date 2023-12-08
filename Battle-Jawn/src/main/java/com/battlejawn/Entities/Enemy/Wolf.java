package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("WOLF")
public class Wolf extends Enemy {

    public Wolf() {
        super("Wolf", 50, 50, 0, 0, 10, LocalDateTime.now());
    }

}
