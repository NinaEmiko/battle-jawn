package com.battlejawn.HeroMove;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.HealerTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class Run {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;
    private final BattleStatusService battleStatusService;
    private final InventoryService inventoryService;

    public HeroMoveDTO processRun(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());

        boolean gameOver = useRun();
        if (gameOver) {
            if (Objects.equals(hero.getRole(), "Healer")){
                HealerTree healerTree = (HealerTree) hero.getTalentTree();
                if (healerTree.isSurvivalInstincts()){
                    hero.setHealth(hero.getMaxHealth());
                }
            }
            hero.setRunCount(hero.getRunCount() + 1);
            heroService.updateHero(hero);
            String newMessage = "You successfully ran away!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, true);
        } else {
            String newMessage = "You could not get away!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
        }
    }
    public boolean useRun() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 45;
    }
}
