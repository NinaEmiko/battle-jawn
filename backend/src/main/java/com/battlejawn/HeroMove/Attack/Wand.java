package com.battlejawn.HeroMove.Attack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.Missable;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Wand implements Missable, Attack {
    private int damage;

    public int attack() {
        if (miss()) {
            return 0;
        } else {
            damage = (int) (Math.floor(Math.random() * 5) + 10 /* * user.strength */);
            return damage;
        }
    }

    public boolean miss() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 95;
    }
}
