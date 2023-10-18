package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("DPS")
public class DPS extends Toon {
    
    public DPS() {
        super(90, 90, 2, 3, "DPS", LocalDateTime.now());
    }

}
