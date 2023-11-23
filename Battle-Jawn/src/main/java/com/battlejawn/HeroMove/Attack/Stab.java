package com.battlejawn.HeroMove.Attack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import lombok.Data;

import java.util.logging.Logger;

@Data
public class Stab implements CriticalHit, Missable, Attack {

    private int stabCount;

    public int attack() {

        if (miss()) {
            return 0;
        } else if (criticalHit()){
            setStabCount(stabCount++);
            return (int) ((Math.floor(Math.random() * 17) + 1) /* * user.strength */ * 1.5);
        } else {
            setStabCount(stabCount++);
            return (int) (Math.floor(Math.random() * 17) + 1 /* * user.strength */);
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
