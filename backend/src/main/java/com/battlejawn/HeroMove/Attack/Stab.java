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
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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
        boolean criticalHit = heroMoveHelper.criticalHit(95);
        int damage = heroMoveHelper.getDamage(7, 15, 95);

        DPSTree dpsTree = (DPSTree) hero.getTalentTree();

        if (damage == 0) {
            if (!dpsTree.isImprovedStab3()) {
                hero.setResource(0);
                heroService.updateHero(hero);
            }
        } else {
            damage += (dpsTree.isImprovedStab1()) ? 4 : 0;
            damage += (dpsTree.isImprovedStab2()) ? 3 : 0;
            damage += (criticalHit) ? damage / 2 : 0;
        }
        return processHeroAttack(damage, enemy, battleSessionId, hero);
    }

    private HeroMoveDTO processHeroAttack(int damage, Enemy enemy, Long battleSessionId, Hero hero) {
        int updatedEnemyHealth = enemy.getHealth() - damage;
        boolean gameOver = false;
        String newMessage = heroMoveHelper.getDamageMessage("Wand", damage);
        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);

        if (damage > 0 && hero.getResource() != hero.getMaxResource()) {
            hero.setResource(hero.getResource() + 1);
            heroService.updateHero(hero);
        }

        if (damage > enemy.getHealth()) {
            updatedEnemyHealth = 0;
            gameOver = true;
            String enemyDefeatedMessage = "You have defeated the enemy!";
            hero.setWinCount(hero.getWinCount() + 1);
            heroService.updateHero(hero);
            battleHistoryMessageService.createNewMessage(battleSessionId, enemyDefeatedMessage);
        }
        enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, gameOver);
    }
}
