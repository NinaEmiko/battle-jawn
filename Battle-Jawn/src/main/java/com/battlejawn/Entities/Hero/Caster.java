package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("CASTER")
public class Caster extends Hero {
    
    public Caster() {
        super(90, 90, 3, 3, "Caster", 0, 0, 0, LocalDateTime.now());
    }

}
