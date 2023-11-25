package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("ORC")
public class Orc extends Enemy {

    public Orc() {
        super("Orc", 100, 100, 2, 3, 15, LocalDateTime.now());
    }

}
