package com.battlejawn.EnemyMove;

import com.battlejawn.Interfaces.CriticalHit;
import com.battlejawn.Interfaces.Missable;
import lombok.Data;

@Data
public class SoulEater implements CriticalHit, Missable {
    private int damage;

    public void attack() {
        setDamage(20);

        if (miss()) {
            setDamage(0);
        } else if (criticalHit()){
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

    public boolean miss() {
        int chance = (int) Math.floor(Math.random() * 100);
        if (chance > 95) {
            return true;
        }
        return false;
    }

}
