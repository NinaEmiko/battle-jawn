package com.battlejawn.EnemyMove;

import com.battlejawn.Interfaces.CriticalHit;
import lombok.Data;

@Data   
public class Impale implements CriticalHit {
    private int damage;

    public void attack() {
        setDamage(/* enemy.strength * 1.2 */ 10);

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
