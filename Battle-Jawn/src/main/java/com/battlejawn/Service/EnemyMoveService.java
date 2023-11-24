package com.battlejawn.Service;

import com.battlejawn.Config.HeroMoveDTO;
import com.battlejawn.EnemyMove.Strike;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemyMoveService {
    private final HeroService heroService;
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final EnemyService enemyService;
    private final BattleSessionService battleSessionService;
    private HeroMoveDTO heroMoveDTO;

    public EnemyMoveService(BattleSessionService battleSessionService, EnemyService enemyService, HeroService heroService, BattleHistoryMessageService battleHistoryMessageService) {
        this.battleSessionService = battleSessionService;
        this.enemyService = enemyService;
        this.heroService = heroService;
        this.battleHistoryMessageService = battleHistoryMessageService;
    }

    public HeroMoveDTO enemyMove(Long battleSessionId){
        Enemy enemy = enemyService.getEnemyById(battleSessionService.getBattleSessionById(battleSessionId).getEnemyId());
        Strike strike = new Strike();
        int damage = strike.attack();
        Hero hero = heroService.getHeroById(battleSessionService.getBattleSessionById(battleSessionId).getHeroId());
        int updatedHeroHealth;
        String newMessage;
        if (damage > hero.getHealth()) {
            updatedHeroHealth = 0;
            newMessage = getDamageMessage("Enemy Strike", damage);
            heroService.updateHealthById(updatedHeroHealth, hero.getId());
            String heroDefeatedMessage = "You have been defeated by the enemy!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            battleHistoryMessageService.createNewMessage(battleSessionId, heroDefeatedMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getPotions(), battleHistory, true);
        } else {
            updatedHeroHealth = hero.getHealth() - damage;
            newMessage = getDamageMessage("Enemy Strike", damage);
            heroService.updateHealthById(updatedHeroHealth, hero.getId());
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getPotions(), battleHistory, true);
        }
    }

    public String getDamageMessage(String move, int damage) {
        if (damage > 0) {
            return move + " did " + damage + " damage.";
        } else {
            return move + " missed!";
        }
    }

    public HeroMoveDTO getHeroMoveReturnObject(int enemyHealth, int heroHealth, int potionCount, List<String> battleHistory, boolean gameOver) {
        HeroMoveDTO heroMoveDTO = new HeroMoveDTO();
        heroMoveDTO.setEnemyHealth(enemyHealth);
        heroMoveDTO.setHeroHealth(heroHealth);
        heroMoveDTO.setPotionCount(potionCount);
        heroMoveDTO.setBattleHistory(battleHistory);
        heroMoveDTO.setGameOver(gameOver);
        return heroMoveDTO;
    }

}
