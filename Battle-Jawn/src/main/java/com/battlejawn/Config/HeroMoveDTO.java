package com.battlejawn.Config;

import lombok.Data;

import java.util.ArrayList;

@Data
public class HeroMoveDTO {
    private int enemyHealth;
    private int heroHealth;
    private ArrayList<String> battleHistory;
    private int potionCount;
}
