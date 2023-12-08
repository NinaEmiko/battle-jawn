package com.battlejawn.EnemyMove;

import java.util.logging.Logger;

public class Steal {

    private final Logger logger = Logger.getLogger(Steal.class.getName());
    public boolean useSteal() {
        int chance = (int) Math.floor(Math.random() * 100);
        logger.info("Inside useSteal move method. Chance: " + chance + ".");
        return chance > 40;
    }
}
