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

    public boolean criticalHit(int percentage) {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > percentage;
    }
    public int getDamage(int extraDamage, int baseDamage, int missPercentage){
        if (miss(missPercentage)) {
            return 0;
        } else {
            return (int) (Math.floor(Math.random() * extraDamage) + baseDamage);
        }
    }
    private boolean miss(int missPercentage) {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > missPercentage;
    }

    public boolean useRun() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 45;
    }

    public boolean useSteal() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 40;
    }
}
