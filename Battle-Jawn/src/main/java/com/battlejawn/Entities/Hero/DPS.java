package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("DPS")
public class DPS extends Hero {
    
    public DPS() {
        super(90, 90, 2, 3, "DPS", 0, 0, 0, LocalDateTime.now());
    }

}
