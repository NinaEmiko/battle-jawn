package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("WOLF")
public class Wolf extends Enemy {

    public Wolf() {
        super("Wolf", 50, 50, 1, 10, LocalDateTime.now());
    }

}
