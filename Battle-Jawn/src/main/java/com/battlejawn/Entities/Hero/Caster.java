package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("CASTER")
public class Caster extends Toon {
    
    public Caster() {
        super(90, 90, 3, 3, "Caster", LocalDateTime.now());
    }

}
