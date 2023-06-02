package com.battlejawn.Battle.Jawn.EnemyMove;

import com.battlejawn.Battle.Jawn.Interfaces.CriticalHit;

public class Impale implements CriticalHit {
    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

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
