package com.battlejawn.HeroMove.Heal;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Entities.TalentTree.CasterTree;
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
public class Water {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;
    private final InventoryService inventoryService;
    public HeroMoveDTO processWater(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());
        Inventory inventory = hero.getInventory();
        int waterCount = inventoryService.findItemCount(inventory, "Water");

        if (Objects.equals(hero.getRole(), "Caster")) {
            CasterTree casterTree = (CasterTree) hero.getTalentTree();
            if (casterTree.isResourcefulness1()) {
                if (waterCount < 1) {
                    inventoryService.addToFirstEmptySlot(inventory, "Water");
                    waterCount += 1;
                }
            }
        }

        String newMessage = "Your energy is maxed out.";
        if (waterCount > 0 && hero.getResource() == hero.getMaxResource()){
            switch(hero.getRole()){
                case "Tank":
                    newMessage = "Your power is maxed out.";
                    break;
                case "Healer":
                    newMessage = "Your spirit is maxed out.";
                    break;
                case "Caster":
                    newMessage = "Your magic is maxed out.";
            };
        } else if (waterCount > 0 && hero.getResource() != hero.getMaxResource()) {
            switch (hero.getRole()) {
                case "Tank":
                    TankTree tankTree = (TankTree) hero.getTalentTree();
                    if (tankTree.isHydration()){
                        if (hero.getMaxHealth() - hero.getHealth() < 5){
                            hero.setHealth(hero.getMaxHealth());
                        } else {
                            hero.setHealth(hero.getHealth() + 5);
                        }
                    }
                    newMessage = "You feel empowered.";
                    break;
                case "Healer":
                    HealerTree healerTree = (HealerTree) hero.getTalentTree();
                    if(healerTree.isBotany1() && hero.getHealth() != hero.getMaxHealth()){
                        if (hero.getMaxHealth() - hero.getHealth() < 5){
                            hero.setHealth(hero.getMaxHealth());
                        } else {
                            hero.setHealth(hero.getHealth() + 5);
                        }
                    }
                    newMessage = "Your spirit has risen.";
                    break;
                case "Caster":
                    CasterTree casterTree = (CasterTree) hero.getTalentTree();
                    if (casterTree.isBotany2() && hero.getHealth() != hero.getMaxHealth()) {
                        if (hero.getMaxHealth() - hero.getHealth() < 5){
                            hero.setHealth(hero.getMaxHealth());
                        } else {
                            hero.setHealth(hero.getHealth() + 5);
                        }
                    }
                    newMessage = "Your magic has risen.";
                    break;
                default:
                    newMessage = "Your energy has increased.";
            };
            inventoryService.removeFirstFromInventory(hero.getId(), "Water");
            hero.setResource(hero.getMaxResource());
            heroService.updateHero(hero);
        } else {
            newMessage = "You are out of water!";
        }

        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
    }
}
