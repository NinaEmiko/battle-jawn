package com.battlejawn.HeroMove.Heal;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Potion {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;
    private final BattleStatusService battleStatusService;
    private final InventoryService inventoryService;
    public HeroMoveDTO processPotion(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());
        Inventory inventory = hero.getInventory();
        int potionCount = inventoryService.findItemCount(inventory, "Potion");

        if (potionCount > 0 && hero.getHealth() != hero.getMaxHealth()) {
            int updatedHeroHealth;
            inventoryService.removeFirstFromInventory(hero.getId(), "Potion");
            int healAmount = 30;
            if (healAmount + hero.getHealth() > hero.getMaxHealth()) {
                updatedHeroHealth = hero.getMaxHealth();
            } else {
                updatedHeroHealth = hero.getHealth() + healAmount;
            }
            hero.setHealth(updatedHeroHealth);
            heroService.updateHero(hero);
            String newMessage = "You feel better now.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getResource(), battleHistory, false);
        } else if (potionCount > 0 && hero.getHealth() == hero.getMaxHealth()) {
            String newMessage = "You are at full health.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
        } else {
            String newMessage = "You are out of potions!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
        }
    }


}
