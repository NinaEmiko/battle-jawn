package com.battlejawn.PlayerMove.StrongAttack;

import com.battlejawn.Interfaces.Attack;
import com.battlejawn.Interfaces.CriticalHit;
import com.battlejawn.Interfaces.Missable;
import lombok.Data;

@Data
public class Blast implements CriticalHit, Missable, Attack {

    public int attack() {

        if (miss()) {
            return 0;
        } else if (criticalHit()){
            return (int) (Math.floor(Math.random() /* * user.strength) + user.strength / 4*/) * 1.5);
        } else {
            return (int) Math.floor(Math.random() /* * user.strength) + user.strength / 4*/);
        }
    }

    public boolean criticalHit() {
        int chance = (int) Math.floor(Math.random() * 100);
        if (chance > 90) {
            return true;
        }
        return false;
    }

    public boolean miss() {
        int chance = (int) Math.floor(Math.random() * 100);
        if (chance > 95) {
            return true;
        }
        return false;
    }
}
