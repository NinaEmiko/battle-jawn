package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import lombok.Data;

@Data
public class BackStab implements CriticalHit, Attack {
    private int damage;

    public int attack() {

        if (criticalHit()){
            damage = 40;
        } else {
            damage = 30;
        }
        return damage;
    }

    public boolean criticalHit() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 90;
    }
}
