package com.battlejawn.PlayerMove.StrongAttack;

import com.battlejawn.Interfaces.Attack;
import com.battlejawn.Interfaces.CriticalHit;
import lombok.Data;

@Data
public class BackStab implements CriticalHit, Attack {

    private int damage;

    public void attack() {
        setDamage(/*player.strength * 2*/ 10);

        if (criticalHit()){
            setDamage(damage *= 1.5);
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
