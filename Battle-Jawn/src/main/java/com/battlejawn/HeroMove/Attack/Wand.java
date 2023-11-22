package com.battlejawn.HeroMove.Attack;

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
            return (int) (Math.floor(Math.random() * 15) + 1);
        }
    }

    public boolean miss() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 95;
    }
}
