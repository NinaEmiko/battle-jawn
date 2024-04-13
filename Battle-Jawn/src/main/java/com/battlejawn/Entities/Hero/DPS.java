package com.battlejawn.Entities.Hero;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("DPS")
public class DPS extends Hero {
    private String name;
    public DPS() {

    }
    public DPS(String name) {
        super(name, 90, 90, "DPS", 0, 0, 0, LocalDateTime.now());
    }

}
