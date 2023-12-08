package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("SPIRIT")
public class Spirit extends Enemy {

    public Spirit() {
        super("Spirit", 150, 150, 0, 0, 20, LocalDateTime.now());
    }

}
