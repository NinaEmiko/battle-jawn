package com.battlejawn.Player;

import javax.persistence.Entity;

@Entity
public class Healer extends Player {

    public Healer() {
        super(100, 100, 17, 0, 0, Role.HEALER);
    }
}
