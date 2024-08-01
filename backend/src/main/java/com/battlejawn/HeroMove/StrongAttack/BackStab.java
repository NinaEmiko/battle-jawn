package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Entities.TalentTree.TankTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.*;
import lombok.Data;

import java.util.List;

@Data
public class BackStab {
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
        DPSTree dpsTree = (DPSTree) hero.getTalentTree();

        if (dpsTree.isImprovedBackStab1() && damage != 0){
            damage += 3;
        }
        if (dpsTree.isImprovedBackStab2()  && damage != 0){
            battleStatus.setEnemyParalyzed(true);
            battleStatusService.saveBattleStatus(battleStatus);
        }
        if (criticalHit && damage != 0){
            damage += (damage / 2);
        }
        return processHeroAttack(damage, enemy, battleSessionId, hero, "BackStab");
    }

    public boolean criticalHit(int percent) {
        int chance = (int) Math.floor(Math.random() * 100);
        return chance > percent;
    }
    private int getDamage(){
        return 30;
    }

    public HeroMoveDTO processHeroAttack(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move) {
        int updatedEnemyHealth;
        String newMessage;

        boolean enoughResource = processHeroResource(hero);

        if (!enoughResource) {
            updatedEnemyHealth = enemy.getHealth();
            newMessage = "You do not have enough energy.";
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
    public boolean processHeroResource(Hero hero) {

        if (hero.getResource() < 3) {
            return false;
        } else {
            hero.setResource(hero.getResource() - 3);
            heroService.updateHero(hero);
            return true;
        }
    }
}
