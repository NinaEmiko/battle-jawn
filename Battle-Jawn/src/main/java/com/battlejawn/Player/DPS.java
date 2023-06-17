package com.battlejawn.Player;

import javax.persistence.Entity;

@Entity
public class DPS extends Player {

    public DPS() {
        super(90, 90, 20, 2, 3, Role.DPS);
    }

}
