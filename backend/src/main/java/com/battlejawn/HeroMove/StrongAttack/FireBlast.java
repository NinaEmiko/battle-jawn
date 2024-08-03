package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.AttackInterfaces.Attack;
import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.AttackInterfaces.Missable;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.CasterTree;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.BattleHistoryMessageService;
import com.battlejawn.Service.BattleSessionService;
import com.battlejawn.Service.EnemyService;
import com.battlejawn.Service.HeroService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FireBlast {
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
        int damage = heroMoveHelper.getDamage(5, 15, 95);
        CasterTree casterTree = (CasterTree) hero.getTalentTree();

        damage += (casterTree.isImprovedFireBlast1()) ? hero.getResource() * 6 : hero.getResource() * 5;
        damage += (criticalHit && damage != 0) ? damage / 2 : 0;
        damage += (casterTree.isImprovedFireBlast2() && hero.getResource() == hero.getMaxResource()) ? 3 : 0;

        hero.setResource(0);
        heroService.updateHero(hero);
        return processHeroAttack(damage, enemy, battleSessionId, hero, "FireBlast");
    }

    public HeroMoveDTO processHeroAttack(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move) {
        int updatedEnemyHealth = enemy.getHealth() - damage;
        String newMessage = heroMoveHelper.getDamageMessage(move, damage);
        boolean gameOver = false;
        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);

        if (damage > enemy.getHealth()) {
            gameOver = true;
            updatedEnemyHealth = 0;
            hero.setWinCount(hero.getWinCount() + 1);
            heroService.updateHero(hero);
            battleHistoryMessageService.createNewMessage(battleSessionId, "You have defeated the enemy!");
        }
        enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, gameOver);
    }
}