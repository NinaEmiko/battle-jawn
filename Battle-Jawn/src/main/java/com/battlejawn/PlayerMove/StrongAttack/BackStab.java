package com.battlejawn.PlayerMove.StrongAttack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import lombok.Data;

@Data
public class BackStab implements CriticalHit, Attack {

    public int attack() {

        if (criticalHit()){
            return (int) Math.floor((/*player.strength * 2*/ 10) * 1.5);
        } else {
            return (int) (/*player.strength * 2*/ 10);
        }
    }

    public boolean criticalHit() {
        int chance = (int) Math.floor(Math.random() * 100);
        if (chance > 90) {
            return true;
        }
        return false;
    }
}
