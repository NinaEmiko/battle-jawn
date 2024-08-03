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
        boolean criticalHit = heroMoveHelper.criticalHit(95);
        int damage = heroMoveHelper.getDamage(5, 10, 95);

        if (Objects.equals(hero.getRole(), "Caster")){
            CasterTree casterTree = (CasterTree) hero.getTalentTree();
            damage += (casterTree.isImprovedWand1()) ? 2 : 0;
            damage += (casterTree.isImprovedWand2()) ? 2 : 0;
            if (casterTree.isImprovedWand3() && criticalHit && hero.getResource() != hero.getMaxResource()){
                hero.setResource(hero.getResource() + 1);
                heroService.updateHero(hero);
            }
        } else {
            HealerTree healerTree = (HealerTree) hero.getTalentTree();
            damage += (healerTree.isImprovedWand1()) ? 2 : 0;
            damage += (healerTree.isImprovedWand2()) ? 2 : 0;
            if (healerTree.isImprovedWand3() && criticalHit && hero.getResource() != hero.getMaxResource()){
                hero.setResource(hero.getResource() + 1);
                heroService.updateHero(hero);
            }
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
        }

        if (damage > enemy.getHealth()) {
            updatedEnemyHealth = 0;
            gameOver = true;
            hero.setWinCount(hero.getWinCount() + 1);
            heroService.updateHero(hero);
            battleHistoryMessageService.createNewMessage(battleSessionId, "You have defeated the enemy!");
        }

        enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, gameOver);
    }
}