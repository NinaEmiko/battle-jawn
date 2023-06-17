package com.battlejawn.Player;

import javax.persistence.Entity;

@Entity
public class Caster extends Player {

    public Caster() {
        super(90, 90, 20, 2, 3, Role.CASTER);
    }

}
