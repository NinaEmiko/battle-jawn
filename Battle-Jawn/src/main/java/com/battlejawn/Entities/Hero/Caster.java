package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("CASTER")
public class Caster extends Hero {
    
    public Caster() {
        super(90, 90, 3, 3, "Caster", 0, 0, 0, LocalDateTime.now());
    }

}
