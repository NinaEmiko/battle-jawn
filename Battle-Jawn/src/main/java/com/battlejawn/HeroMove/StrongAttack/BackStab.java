package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import lombok.Data;

@Data
public class BackStab implements CriticalHit, Attack {
    private int damage;

    public int attack() {

        if (criticalHit()){
            damage = (int) ((Math.floor(Math.random() * 15) + 10 /* * user.strength */) * 1.5);
            return damage;
        } else {
            damage = (int) (Math.floor(Math.random() * 15) + 10 /* * user.strength */);
            return damage;
        }
    }

    public boolean criticalHit() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 90;
    }
}
