package com.battlejawn.EnemyMove;

import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import lombok.Data;

@Data
public class ShadowBlast implements CriticalHit, Missable {
    private int damage;
    public int attack() {

        if (miss()) {
            return 0;
        } else if (criticalHit()){
            damage = (int) ((Math.floor(Math.random() * 17) + 10 /* * user.strength */) * 1.5);
            return damage;
        } else {
            damage = (int) (Math.floor(Math.random() * 7) + 10 /* * user.strength */);
            return damage;
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
