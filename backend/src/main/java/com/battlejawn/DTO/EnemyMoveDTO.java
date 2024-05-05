package com.battlejawn.DTO;

import lombok.Data;
import java.util.List;

@Data
public class EnemyMoveDTO {
    private int enemyHealth;
    private int heroHealth;
    private int heroResource;
    private List<String> battleHistory;
    private int potionCount;
    private boolean gameOver;
}
