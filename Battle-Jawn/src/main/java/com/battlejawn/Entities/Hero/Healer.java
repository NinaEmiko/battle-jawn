package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("HEALER")
public class Healer extends Toon {

    public Healer() {
        super(100, 100, 0, 0, "Healer", LocalDateTime.now());
    }
    
}
