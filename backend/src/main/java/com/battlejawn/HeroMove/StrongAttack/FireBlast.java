package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import lombok.Data;

@Data
public class FireBlast implements CriticalHit, Missable, Attack {
    private int damage;

    public int attack() {

        if (miss()) {
            damage = 0;
        } else if (criticalHit()){
            damage = (int) ((Math.floor(Math.random() * 5) + 15 /* * user.strength */) * 1.5);
        } else {
            damage = (int) (Math.floor(Math.random() * 5) + 15 /* * user.strength */);
        }
        return damage;
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