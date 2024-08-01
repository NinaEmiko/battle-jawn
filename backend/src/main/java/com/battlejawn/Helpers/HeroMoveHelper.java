package com.battlejawn.Helpers;

import com.battlejawn.DTO.HeroMoveDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroMoveHelper {
    public HeroMoveDTO getHeroMoveReturnObject(int enemyHealth, int heroHealth, int heroResource, List<String> battleHistory, boolean gameOver) {
        HeroMoveDTO heroMoveDTO = new HeroMoveDTO();
        heroMoveDTO.setEnemyHealth(enemyHealth);
        heroMoveDTO.setHeroHealth(heroHealth);
        heroMoveDTO.setHeroResource(heroResource);
        heroMoveDTO.setBattleHistory(battleHistory);
        heroMoveDTO.setGameOver(gameOver);
        return heroMoveDTO;
    }

    public String getDamageMessage(String move, int damage) {
        if (damage > 0) {
            return move + " did " + damage + " damage.";
        } else {
            return move + " missed!";
        }
    }
}
