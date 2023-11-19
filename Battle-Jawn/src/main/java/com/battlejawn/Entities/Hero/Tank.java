package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("TANK")
public class Tank extends Hero {

    public Tank() {
        super(120, 120, 3, 3, "Tank", LocalDateTime.now());
    }
    
}
