package com.battlejawn.HeroMove;

public class Steal {

    public boolean useSteal() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 45;
    }
}
