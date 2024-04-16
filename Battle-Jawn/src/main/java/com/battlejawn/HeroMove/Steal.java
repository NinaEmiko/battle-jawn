package com.battlejawn.HeroMove;

import org.springframework.stereotype.Component;

@Component
public class Steal {
    public boolean useSteal() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 40;
    }
}
