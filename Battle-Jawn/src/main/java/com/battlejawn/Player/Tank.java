package com.battlejawn.Player;

import javax.persistence.Entity;

@Entity
public class Tank extends Player {

    public Tank() {
        super(120, 120, 15, 3, 3, Role.TANK);
    }
}
