package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("SPIRIT")
public class Spirit extends Enemy {

    public Spirit() {
        super("Spirit", 150, 150, 1, 20, LocalDateTime.now());
    }

}
