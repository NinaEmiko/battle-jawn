package com.battlejawn.EnemyMove;

import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import lombok.Data;

@Data
public class Bite implements CriticalHit, Missable {
    private int damage;
    public int attack() {

        if (miss()) {
            return 0;
        } else if (criticalHit()){
            damage = (int) ((Math.floor(Math.random() * 10) + 10 /* * user.strength */) * 1.5);
            return damage;
        } else {
            return (int) (Math.floor(Math.random() * 10) + 1  /* * user.strength */);
        }
    }

    public boolean criticalHit() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 90;
    }

    public boolean miss() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 95;
    }
}
