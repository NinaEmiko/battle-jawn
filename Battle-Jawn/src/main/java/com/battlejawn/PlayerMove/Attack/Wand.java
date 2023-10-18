package com.battlejawn.PlayerMove.Attack;

import com.battlejawn.Interfaces.Missable;

import lombok.Data;

@Data
public class Wand implements Missable {
    private int damage;

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