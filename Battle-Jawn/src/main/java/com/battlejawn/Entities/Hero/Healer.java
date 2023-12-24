package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("HEALER")
public class Healer extends Hero {
    private String name;
    public Healer() {

    }
    public Healer(String name) {
        super(name, 100, 100, 0, 0, "Healer", 0, 0, 0, LocalDateTime.now());
    }
    
}
