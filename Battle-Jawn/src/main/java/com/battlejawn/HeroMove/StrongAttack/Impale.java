package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import lombok.Data;

@Data
public class Impale implements CriticalHit, Missable, Attack {

    public int attack() {

        if (miss()) {
            return 0;
        } else if (criticalHit()){
            return (int) (( /* player.strength * 1.2*/ 10) * 1.5);
        } else {
            return ( /* player.strength * 1.2*/ 10);
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