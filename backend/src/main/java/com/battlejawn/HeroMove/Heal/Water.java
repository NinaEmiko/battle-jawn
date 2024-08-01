package com.battlejawn.HeroMove.Heal;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
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
public class Water {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;
    private final BattleStatusService battleStatusService;
    private final InventoryService inventoryService;
    public HeroMoveDTO processWater(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());
        Inventory inventory = hero.getInventory();
        int waterCount = inventoryService.findItemCount(inventory, "Water");

        String newMessage;
        if (waterCount > 0 && hero.getResource() == hero.getMaxResource()){
            newMessage = switch(hero.getRole()){
                case "Tank" -> "Your power is maxed out.";
                case "Healer" -> "Your spirit is maxed out.";
                case "Caster" -> "Your magic is maxed out.";
                default -> "Your energy is maxed out.";
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
                    newMessage = "Your spirit has risen.";
                    break;
                case "Caster":
                    newMessage = "Your magic has risen.";
                    break;
                default:
                    newMessage = "Your energy has increased.";
            };
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
