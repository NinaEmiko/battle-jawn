package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import lombok.Data;

@Data
public class Holy implements CriticalHit, Missable, Attack {

    public int attack() {

        //If enemy is spirit, do double damage

        if (miss()) {
            return 0;
        } else if (criticalHit()){
            return (int) ((Math.random()/* * user.strength) + user.strength / 3*/) * 1.5);
        } else {
            return (int) (Math.random()/* * user.strength) + user.strength / 3*/);
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