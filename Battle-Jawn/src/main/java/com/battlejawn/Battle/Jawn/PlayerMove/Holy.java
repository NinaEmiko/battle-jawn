package com.battlejawn.Battle.Jawn.PlayerMove;

import com.battlejawn.Battle.Jawn.Interfaces.CriticalHit;
import com.battlejawn.Battle.Jawn.Interfaces.Missable;

public class Holy implements CriticalHit, Missable {

    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void attack() {
        setDamage((int) (Math.random()/* * user.strength) + user.strength / 3*/));

        //If enemy is spirit, do double damage

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
