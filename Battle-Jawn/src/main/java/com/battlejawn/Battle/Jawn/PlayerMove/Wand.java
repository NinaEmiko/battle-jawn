package com.battlejawn.Battle.Jawn.PlayerMove;

import com.battlejawn.Battle.Jawn.Interfaces.Missable;

public class Wand implements Missable {

    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void attack() {
        setDamage((int) Math.floor(/*user.strength * .75*/10));

        if (miss()) {
            setDamage(0);
        }
    }

    public boolean miss() {
        int chance = (int) Math.floor(Math.random() * 100);
        if (chance > 95) {
            return true;
        }
        return false;
    }
}
