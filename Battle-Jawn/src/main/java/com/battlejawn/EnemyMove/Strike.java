package com.battlejawn.EnemyMove;

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
            return (int) ((Math.floor(Math.random() * 17) + 1 ) /* * user.strength */ * 1.5);
        } else {
            return (int) (Math.floor(Math.random() * 17) + 1  /* * user.strength */);
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

    public boolean stagger() {
        int chance = (int) Math.floor(Math.random() * 10);
        return chance > 95;
    }
}
