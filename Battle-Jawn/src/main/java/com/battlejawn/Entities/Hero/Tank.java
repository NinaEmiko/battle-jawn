package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("TANK")
public class Tank extends Hero {

    public Tank() {
        super(120, 120, 3, 3, "Tank", 0, 0, 0, LocalDateTime.now());
    }
    
}
