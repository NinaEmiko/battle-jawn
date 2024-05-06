package com.battlejawn.HeroMove.Attack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import lombok.Data;

@Data
public class Stab implements CriticalHit, Missable, Attack {

    private int damage;
    private int stabCount;

    public int attack() {
        if (miss()) {
            return 0;
        } else if (criticalHit()){
            setStabCount(stabCount++);
            damage = (int) ((Math.floor(Math.random() * 7) + 15) /* * user.strength */ * 1.5);
            return damage;
        } else {
            setStabCount(stabCount++);
            damage = (int) (Math.floor(Math.random() * 7) + 15 /* * user.strength */);
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
