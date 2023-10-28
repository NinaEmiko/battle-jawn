package com.battlejawn.PlayerMove.Attack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.Missable;

import lombok.Data;

@Data
public class Wand implements Missable, Attack {
    private int damage;

    public int attack() {
        if (miss()) {
            return 0;
        } else {
            return ((int) Math.floor(/*user.strength * .75*/10));
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
