package com.battlejawn.HeroMove.Attack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import lombok.Data;

import java.util.logging.Logger;

@Data
public class Stab implements CriticalHit, Missable, Attack {

    private int stabCount;
    private final Logger logger = Logger.getLogger(Stab.class.getName());

    public int attack() {
        logger.info("Inside Stab attack method.");

        if (miss()) {
            logger.info("Stab missed.");
            return 0;
        } else if (criticalHit()){
            logger.info("Stab critical hit.");
            setStabCount(stabCount++);
            return (int) ((Math.floor(Math.random() * 15) + 1) /* * user.strength */ * 1.5);
        } else {
            logger.info("Stab hit.");
            setStabCount(stabCount++);
            return (int) (Math.floor(Math.random() * 15) + 1 /* * user.strength */);
        }
    }

    public boolean criticalHit() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 90;
    }

    public boolean miss() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 95;
    }
}
