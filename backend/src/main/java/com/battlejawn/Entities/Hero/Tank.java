package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("TANK")
public class Tank extends Hero {
    private String name;

    public Tank() {

    }
    public Tank(String name) {

        super(name, 120, 120,3, 3, "Tank", 0, 0, 0, LocalDateTime.now());
    }
    
}
