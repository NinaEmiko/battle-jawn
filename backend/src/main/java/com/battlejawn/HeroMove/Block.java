package com.battlejawn.HeroMove;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.HealerTree;
import com.battlejawn.Entities.TalentTree.TankTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Block {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;
    private final BattleStatusService battleStatusService;

    public HeroMoveDTO processBlock(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());
        BattleStatus battleStatus = battleSession.getBattleStatus();
        String newMessage = "You prepared to block.";
        int updatedEnemyHealth;
        boolean gameOver = false;
        if (hero.getResource() < 1) {
            newMessage = "You do not have enough power.";
        } else {
            TankTree tankTree = (TankTree) hero.getTalentTree();
            if (tankTree.isDesperation() && hero.getHealth() < hero.getMaxHealth() / 5){
                battleHistoryMessageService.createNewMessage(battleSessionId, "You struck the enemy!");
                int damage = heroMoveHelper.getDamage(5, 10, 100);
                String damageMessage = heroMoveHelper.getDamageMessage("Strike", damage);

                if (damage > enemy.getHealth()) {
                    updatedEnemyHealth = 0;
                    gameOver = true;
                    hero.setWinCount(hero.getWinCount() + 1);
                    heroService.updateHero(hero);
                    battleHistoryMessageService.createNewMessage(battleSessionId, damageMessage);
                    battleHistoryMessageService.createNewMessage(battleSessionId, "You have defeated the enemy!");
                } else {
                    battleHistoryMessageService.createNewMessage(battleSessionId, damageMessage);
                    updatedEnemyHealth = enemy.getHealth() - damage;
                    battleStatus.setHeroBlocking(true);
                    battleStatusService.saveBattleStatus(battleStatus);
                    hero.setResource(hero.getResource() - 1);
                    heroService.updateHero(hero);
                }
                enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
            }
    }

        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, gameOver);
    }
}
