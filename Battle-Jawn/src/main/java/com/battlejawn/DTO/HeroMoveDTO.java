package com.battlejawn.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class HeroMoveDTO {
    private int enemyHealth;
    private int heroHealth;
    private List<String> battleHistory;
    private int potionCount;
    private boolean gameOver;
}