package com.battlejawn.HeroMove.Attack;

import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.CasterTree;
import com.battlejawn.Entities.TalentTree.HealerTree;
import com.battlejawn.Service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class Wand {

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

        if (Objects.equals(hero.getRole(), "Caster")){
            CasterTree casterTree = (CasterTree) hero.getTalentTree();
            if (casterTree.isImprovedWand1()){
                damage += 2;
            }
            if (casterTree.isImprovedWand2()){
                damage += 2;
            }
            if (casterTree.isImprovedWand3() && criticalHit){
                if (hero.getResource() != hero.getMaxResource()) {
                    hero.setResource(hero.getResource() + 1);
                    heroService.updateHero(hero);
                }
            }
        } else if (Objects.equals(hero.getRole(), "Healer")){
            HealerTree healerTree = (HealerTree) hero.getTalentTree();
            if (healerTree.isImprovedWand1()){
                damage += 2;
            }
            if (healerTree.isImprovedWand2()){
                damage += 2;
            }
            if (healerTree.isImprovedWand3() && criticalHit){
                if (hero.getResource() != hero.getMaxResource()) {
                    hero.setResource(hero.getResource() + 1);
                    heroService.updateHero(hero);
                }
            }
        }

        return processHeroAttack(damage, enemy, battleSessionId, hero, "Wand");
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
            return (int) (Math.floor(Math.random() * 5) + 10);
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
