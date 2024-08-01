package com.battlejawn.HeroMove.Attack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Entities.TalentTree.TankTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.BattleHistoryMessageService;
import com.battlejawn.Service.BattleSessionService;
import com.battlejawn.Service.EnemyService;
import com.battlejawn.Service.HeroService;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Stab {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;

    public HeroMoveDTO attack(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());

        boolean criticalHit = criticalHit(95);
        int damage = getDamage();
        DPSTree dpsTree = (DPSTree) hero.getTalentTree();

        if (damage == 0) {
            if (!dpsTree.isImprovedStab3()) {
                hero.setResource(0);
                heroService.updateHero(hero);
            }
        } else {
            if (dpsTree.isImprovedStab1()) {
                damage += 4;
            }
            if (dpsTree.isImprovedStab2()) {
                damage += 3;
            }
        }
        if (criticalHit && damage != 0){
            damage += (damage / 2);
        }
        return processHeroAttack(damage, enemy, battleSessionId, hero, "Stab");
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
            return (int) (Math.floor(Math.random() * 7) + 15);
        }
    }

    public HeroMoveDTO processHeroAttack(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move) {
        int updatedEnemyHealth;
        String newMessage;

        if (damage > enemy.getHealth()) {
            updatedEnemyHealth = 0;
            if (damage > 0){
                if (hero.getResource() != hero.getMaxResource()) {
                    hero.setResource(hero.getResource() + 1);
                    heroService.updateHero(hero);
                }
            }
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
            if (damage > 0){
                if (hero.getResource() != hero.getMaxResource()) {
                    hero.setResource(hero.getResource() + 1);
                    heroService.updateHero(hero);
                }
            }
            newMessage = heroMoveHelper.getDamageMessage(move, damage);
            enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveHelper.getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, false);
        }
    }
}
