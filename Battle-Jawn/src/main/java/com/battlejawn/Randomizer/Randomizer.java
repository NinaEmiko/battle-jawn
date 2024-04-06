package com.battlejawn.Randomizer;

import com.battlejawn.Controllers.HeroController;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Randomizer {
    private final Logger logger = Logger.getLogger(HeroController.class.getName());
    public int getRandomInt(int ceiling) {
        return (int) Math.floor(Math.random() * ceiling) + 1;
    }
    public long getRandomLong(Long ceiling) {
        return (long) Math.floor(Math.random() * ceiling) + 1;
    }
}
