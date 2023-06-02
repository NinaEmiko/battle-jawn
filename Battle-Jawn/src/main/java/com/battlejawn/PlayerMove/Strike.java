package com.battlejawn.PlayerMove;

import com.battlejawn.Interfaces.CriticalHit;
import com.battlejawn.Interfaces.Missable;
import com.battlejawn.Interfaces.Stagger;

public class Strike implements CriticalHit, Missable, Stagger {

    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void attack() {
        setDamage((int) Math.floor(Math.random() /* * user.strength */));

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

    public boolean stagger() {
        int chance = (int) Math.floor(Math.random() * 10);
        if (chance > 95) {
            return true;
        }
        return false;
    }
}
