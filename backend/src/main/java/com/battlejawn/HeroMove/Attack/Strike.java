package com.battlejawn.HeroMove.Attack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import com.battlejawn.AttackInterfaces.Stagger;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.CasterTree;
import com.battlejawn.Entities.TalentTree.HealerTree;
import com.battlejawn.Entities.TalentTree.TankTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.BattleHistoryMessageService;
import com.battlejawn.Service.BattleSessionService;
import com.battlejawn.Service.EnemyService;
import com.battlejawn.Service.HeroService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class Strike {
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

        TankTree tankTree = (TankTree) hero.getTalentTree();

        if (tankTree.isImprovedStrike1() && damage != 0){
            damage += 3;
        }
        if (tankTree.isImprovedStrike2()  && damage != 0){
            damage += 2;
        }
        if (tankTree.isImprovedStrike3()  && damage != 0 && criticalHit){
            if (hero.getResource() != hero.getMaxResource()) {
                hero.setResource(hero.getResource() + 1);
                heroService.updateHero(hero);
            }
        }
        if (criticalHit && damage != 0){
            damage += (damage / 2);
        }
        return processHeroAttack(damage, enemy, battleSessionId, hero, "Strike");
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
            return (int) (Math.floor(Math.random() * 6) + 10);
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
