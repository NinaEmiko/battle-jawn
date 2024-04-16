package com.battlejawn.EnemyMove;

public class Paralyze {
    public boolean useParalyze() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 45;
    }
}
