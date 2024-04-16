package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Randomizer.Randomizer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoinProcessorService {
    private Randomizer randomizer;
    public Long processCoins(Enemy enemy) {

        if (enemy.getName().equals("Thief")) {
            return processCoinsThief(enemy.getLevel());
        } else if (enemy.getName().equals("Orc")) {
            return processCoinsOrc(enemy.getLevel());
        }
        return 0L;
    }
    private Long processCoinsThief(int level) {
        return switch (level) {
            case 1, 2, 3 -> randomizer.getRandomLong(3L);
            case 4, 5 -> randomizer.getRandomLong(4L);
            case 6, 7 -> randomizer.getRandomLong(5L);
            case 8 -> randomizer.getRandomLong(6L);
            case 9 -> randomizer.getRandomLong(7L);
            default -> randomizer.getRandomLong(10L);
        };
    }
    private Long processCoinsOrc(int level){
        return switch (level) {
            case 1, 2, 3 -> randomizer.getRandomLong(2L);
            case 4, 5 -> randomizer.getRandomLong(3L);
            case 6, 7 -> randomizer.getRandomLong(4L);
            case 8 -> randomizer.getRandomLong(5L);
            case 9 -> randomizer.getRandomLong(6L);
            default -> randomizer.getRandomLong(8L);
        };
    }
}
