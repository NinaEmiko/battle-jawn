package com.battlejawn.HeroMove;

public class Run {

    public boolean useRun() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 45;
    }
}
