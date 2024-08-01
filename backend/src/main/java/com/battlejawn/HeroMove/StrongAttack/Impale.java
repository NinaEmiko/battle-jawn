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

        boolean criticalHit = criticalHit(95);
        int damage = getDamage();
        TankTree tankTree = (TankTree) hero.getTalentTree();

        if (tankTree.isImprovedImpale1() && damage != 0){
            damage += 2;
        }
        if (tankTree.isImprovedImpale2()  && damage != 0){
            damage += 1;
        }
        if (tankTree.isImprovedImpale2()  && damage != 0){
            battleStatus.setEnemyParalyzed(true);
            battleStatusService.saveBattleStatus(battleStatus);
        }
        if (criticalHit && damage != 0){
            damage += (damage / 2);
        }
        return processHeroAttack(damage, enemy, battleSessionId, hero, "Impale", tankTree);
    }
    public boolean miss() {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > 95;
    }

    public boolean criticalHit(int percent) {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > percent;
    }
    private int getDamage(){
        if (miss()) {
            return 0;
        } else {
            return (int) (Math.floor(Math.random() * 5) + 20);
        }
    }

    public HeroMoveDTO processHeroAttack(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move, TankTree tankTree) {
        int updatedEnemyHealth;
        String newMessage;

        boolean enoughResource = processHeroResource(tankTree, hero);

        if (!enoughResource) {
            updatedEnemyHealth = enemy.getHealth();
            newMessage = "You do not have enough power.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveHelper.getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, false);
        } else if (damage > enemy.getHealth()) {
            updatedEnemyHealth = 0;
            newMessage = heroMoveHelper.getDamageMessage(move, damage);
            enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
            String enemyDefeatedMessage = "You have defeated the enemy!";
            hero.setWinCount(hero.getWinCount() + 1);
            heroService.updateHero(hero);
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            battleHistoryMessageService.createNewMessage(battleSessionId, enemyDefeatedMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveHelper.getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, true);
        } else {
            updatedEnemyHealth = enemy.getHealth() - damage;
            newMessage = heroMoveHelper.getDamageMessage(move, damage);
            enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveHelper.getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, false);
        }
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