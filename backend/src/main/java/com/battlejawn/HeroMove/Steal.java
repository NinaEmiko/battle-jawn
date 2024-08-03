package com.battlejawn.HeroMove;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Steal {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;
    private final InventoryService inventoryService;

    public HeroMoveDTO processSteal(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());
        Inventory inventory = hero.getInventory();

        DPSTree dpsTree = (DPSTree) hero.getTalentTree();
        int emptySpaces = inventoryService.getEmptySlotSize(hero.getId());

        if (enemy.getPotions() > 0 && emptySpaces > 0) {
            boolean stealSuccess = heroMoveHelper.useSteal();
            if (stealSuccess) {
                inventoryService.addToFirstEmptySlot(inventory, "Potion");
                int updatedEnemyPotionCount = enemy.getPotions() - 1;
                if (dpsTree.isElation()){
                    hero.setResource(3);
                }
                if (dpsTree.isImprovedSteal1()){
                    Long coins = heroMoveHelper.stealCoins();
                    hero.setCoins(coins);
                    battleHistoryMessageService.createNewMessage(battleSessionId, "You stole " + coins + " coins!");
                }
                if (dpsTree.isImprovedSteal2()){
                    Long coins = heroMoveHelper.stealCoins();
                    hero.setCoins(coins);
                    battleHistoryMessageService.createNewMessage(battleSessionId, "You stole " + coins + " coins!");
                }
                heroService.updateHero(hero);
                enemyService.updatePotionCountById(updatedEnemyPotionCount, enemy.getId());
                battleHistoryMessageService.createNewMessage(battleSessionId, "You stole a potion!");
            } else {
                battleHistoryMessageService.createNewMessage(battleSessionId, "You didn't find anything.");
            }
        } else {
            battleHistoryMessageService.createNewMessage(battleSessionId, "You didn't find anything.");
        }

        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
    }
}
