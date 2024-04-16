package com.battlejawn.EnemyMove;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EnemySteal {

    private final Logger logger = Logger.getLogger(EnemySteal.class.getName());
    public boolean useSteal() {
        int chance = (int) Math.floor(Math.random() * 100);
        logger.info("Inside useSteal move method. Chance: " + chance + ".");
        return chance > 40;
    }
}
