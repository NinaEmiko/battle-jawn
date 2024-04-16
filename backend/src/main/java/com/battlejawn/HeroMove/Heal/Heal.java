package com.battlejawn.HeroMove.Heal;

import com.battlejawn.AttackInterfaces.CriticalHit;

public class Heal implements CriticalHit {

    public int useHeal() {
        if (criticalHit()){
            return 45;
        } else {
            return 30;
        }
    }

    @Override
    public boolean criticalHit() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 90;
    }
}
