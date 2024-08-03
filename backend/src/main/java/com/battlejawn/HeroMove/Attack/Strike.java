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
        boolean criticalHit = heroMoveHelper.criticalHit(95);
        int damage = heroMoveHelper.getDamage(6, 10, 95);

        TankTree tankTree = (TankTree) hero.getTalentTree();
        damage += (tankTree.isImprovedStrike1()) ? 3 : 0;
        damage += (tankTree.isImprovedStrike2()) ? 2 : 0;
        damage += (criticalHit && damage != 0) ? damage / 2 : 0;
        if (tankTree.isImprovedStrike3()  && damage != 0 && criticalHit){
            if (hero.getResource() != hero.getMaxResource()) {
                hero.setResource(hero.getResource() + 1);
                heroService.updateHero(hero);
            }
        }
        return processHeroAttack(damage, enemy, battleSessionId, hero);
    }

    private HeroMoveDTO processHeroAttack(int damage, Enemy enemy, Long battleSessionId, Hero hero) {
        int updatedEnemyHealth = enemy.getHealth() - damage;
        String newMessage = heroMoveHelper.getDamageMessage("Strike", damage);
        boolean gameOver = false;
        enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);

        if (damage > 0 && hero.getResource() != hero.getMaxResource()) {
            hero.setResource(hero.getResource() + 1);
            heroService.updateHero(hero);
        }

        if (damage > enemy.getHealth()) {
            updatedEnemyHealth = 0;
            gameOver = true;
            hero.setWinCount(hero.getWinCount() + 1);
            heroService.updateHero(hero);
            battleHistoryMessageService.createNewMessage(battleSessionId, "You have defeated the enemy!");
        }
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, gameOver);
    }
}
