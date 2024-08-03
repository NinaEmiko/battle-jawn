package com.battlejawn.HeroMove.Heal;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Entities.TalentTree.HealerTree;
import com.battlejawn.Entities.TalentTree.TankTree;
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
public class Potion {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;
    private final InventoryService inventoryService;
    public HeroMoveDTO processPotion(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());
        Inventory inventory = hero.getInventory();
        int potionCount = inventoryService.findItemCount(inventory, "Potion");
        String newMessage = "You are out of potions!";

        if (potionCount > 0 && hero.getHealth() != hero.getMaxHealth()) {
            int updatedHeroHealth;
            inventoryService.removeFirstFromInventory(hero.getId(), "Potion");
            int healAmount = 30;
            if (healAmount + hero.getHealth() > hero.getMaxHealth()) {
                updatedHeroHealth = hero.getMaxHealth();
            } else {
                updatedHeroHealth = hero.getHealth() + healAmount;
            }
            if (Objects.equals(hero.getRole(), "Healer")){
                HealerTree healerTree = (HealerTree) hero.getTalentTree();
                if (healerTree.isBotany2() && hero.getResource() < hero.getMaxResource()){
                    hero.setResource(hero.getResource() + 1);
                }
            }
            if (Objects.equals(hero.getRole(), "Tank")){
                TankTree tankTree = (TankTree) hero.getTalentTree();
                if (tankTree.isFinalStand() && potionCount == 1){
                    if (hero.getMaxHealth() - hero.getHealth() < 60){
                        hero.setHealth(hero.getMaxHealth());
                    } else {
                        hero.setHealth(hero.getHealth() + 60);
                    }
                }
            }
            hero.setHealth(updatedHeroHealth);
            heroService.updateHero(hero);
            newMessage = "You feel better now.";
        } else if (potionCount > 0 && hero.getHealth() == hero.getMaxHealth()) {
            newMessage = "You are at full health.";
        }
        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
    }
}