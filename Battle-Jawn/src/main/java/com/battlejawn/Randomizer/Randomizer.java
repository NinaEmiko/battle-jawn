package com.battlejawn.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class Randomizer {
    public int getRandomInt(int ceiling) {
        return (int) Math.floor(Math.random() * ceiling) + 1;
    }
    public long getRandomLong(Long ceiling) {
        return (long) Math.floor(Math.random() * ceiling) + 1;
    }
}
