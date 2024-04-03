package com.battlejawn.Entities.Enemy;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity 
@DiscriminatorValue("THIEF")
public class Thief extends Enemy {

    public Thief(int level) {
        super("Thief", 90, 90, 2, 4, 17, LocalDateTime.now(), level);
    }

}
