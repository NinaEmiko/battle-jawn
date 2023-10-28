package com.battlejawn.PlayerMove.Attack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import com.battlejawn.AttackInterfaces.Stagger;
import lombok.Data;

@Data
public class Strike implements CriticalHit, Missable, Stagger, Attack {

    public int attack() {

        if (miss()) {
            return 0;
        } else if (criticalHit()){
            return (int) Math.floor(Math.random() /* * user.strength */ * 1.5);
        } else {
            return (int) Math.floor(Math.random() /* * user.strength */);
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

    public boolean stagger() {
        int chance = (int) Math.floor(Math.random() * 10);
        if (chance > 95) {
            return true;
        }
        return false;
    }
}
