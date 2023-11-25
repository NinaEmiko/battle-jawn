package com.battlejawn.EnemyMove;

import com.battlejawn.AttackInterfaces.CriticalHit;
import lombok.Data;

@Data   
public class Impale implements CriticalHit {
    public int attack() {

        if (criticalHit()){
            return (int) ((Math.floor(Math.random() * 17) + 1 ) /* * user.strength */ * 1.5);
        } else {
            return (int) (Math.floor(Math.random() * 17) + 1  /* * user.strength */);
        }
    }

    public boolean criticalHit() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 90;
    }
}
