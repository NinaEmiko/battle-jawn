package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("CASTER")
public class Caster extends Hero {
    private String name;
    public Caster() {

    }
    public Caster(String name) {
        super(name, 90, 90,3, 3, "Caster", 0, 0, 0, LocalDateTime.now());
    }

}
