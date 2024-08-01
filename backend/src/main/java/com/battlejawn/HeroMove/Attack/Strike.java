package com.battlejawn.HeroMove.Attack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import com.battlejawn.AttackInterfaces.Stagger;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Strike implements Missable, Stagger, Attack {
    private int damage;

    public int attack() {

        if (miss()) {
            return 0;
        } else {
            damage = (int) (Math.floor(Math.random() * 6) + 10 /* * user.strength */);
            return damage;
        }
    }

    public boolean miss() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 95;
    }

    public boolean stagger() {
        int chance = (int) Math.floor(Math.random() * 10);
        return chance > 95;
    }
}
