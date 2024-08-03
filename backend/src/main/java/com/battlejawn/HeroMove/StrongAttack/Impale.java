package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
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
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class Impale {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;
    private final BattleStatusService battleStatusService;

    public HeroMoveDTO attack(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());
        BattleStatus battleStatus = battleSession.getBattleStatus();

        boolean criticalHit = heroMoveHelper.criticalHit(95);
        int damage = heroMoveHelper.getDamage(5, 20, 95);
        TankTree tankTree = (TankTree) hero.getTalentTree();

        if (damage != 0) {
            damage += (tankTree.isImprovedImpale1()) ? 2 : 0;
            damage += (tankTree.isImprovedImpale2()) ? 1 : 0;
            damage += (criticalHit) ? damage / 2 : 0;
            if (tankTree.isImprovedImpale2()) {
                battleStatus.setEnemyParalyzed(true);
                battleStatusService.saveBattleStatus(battleStatus);
            }
        }

        return processHeroAttack(damage, enemy, battleSessionId, hero, "Impale", tankTree);
    }

    public HeroMoveDTO processHeroAttack(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move, TankTree tankTree) {
        int updatedEnemyHealth = enemy.getHealth() - damage;
        String newMessage = heroMoveHelper.getDamageMessage(move, damage);
        boolean gameOver = false;


        if (!processHeroResource(tankTree, hero)) {
            updatedEnemyHealth = enemy.getHealth();
            newMessage = "You do not have enough power.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            } else if (damage > enemy.getHealth()) {
            updatedEnemyHealth = 0;
            gameOver = true;
            hero.setWinCount(hero.getWinCount() + 1);
            heroService.updateHero(hero);
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            battleHistoryMessageService.createNewMessage(battleSessionId, "You have defeated the enemy!");
            } else {
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        }
        enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, gameOver);
    }
    public boolean processHeroResource(TankTree tankTree, Hero hero) {

        if (tankTree.isTitan()){
            if (hero.getResource() < 2) {
                return false;
            } else {
                hero.setResource(hero.getResource() - 2);
                heroService.updateHero(hero);
                return true;
            }
        } else {
            if (hero.getResource() < 3) {
                return false;
            } else {
                hero.setResource(hero.getResource() - 3);
                heroService.updateHero(hero);
                return true;
            }
        }
    }
}