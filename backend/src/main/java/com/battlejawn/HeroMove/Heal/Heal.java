package com.battlejawn.HeroMove.Heal;

import com.battlejawn.AttackInterfaces.CriticalHit;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Entities.TalentTree.HealerTree;
import com.battlejawn.Helpers.HeroMoveHelper;
import com.battlejawn.Service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Heal {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final HeroMoveHelper heroMoveHelper;

    public HeroMoveDTO useHeal(Long battleSessionId) {
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());

        boolean criticalHit = heroMoveHelper.criticalHit(95);
        int healAmount = 30;

        HealerTree healerTree = (HealerTree) hero.getTalentTree();

        healAmount += (healerTree.isImprovedHeal1()) ? 5 : 0;
        healAmount += (healerTree.isImprovedHeal2()) ? 5 : 0;
        healAmount += (healerTree.isImprovedHeal3() && criticalHit) ? healAmount / 2 : 0;

        return processHeroHeal(healAmount, enemy, battleSessionId, hero);
    }

    public HeroMoveDTO processHeroHeal(int healAmount, Enemy enemy, Long battleSessionId, Hero hero) {
        HealerTree healerTree = (HealerTree) hero.getTalentTree();
        int updatedHeroHealth;
        String newMessage;
        if (hero.getResource() == 0 && !healerTree.isSpirituallyAttuned()) {
            newMessage = "You do not have enough spirit.";
            updatedHeroHealth = hero.getHealth();
        } else if (healAmount + hero.getHealth() > hero.getMaxHealth()) {
            updatedHeroHealth = hero.getMaxHealth();
            if (!healerTree.isSpirituallyAttuned()){
                hero.setResource(hero.getResource() - 1);
            }
            newMessage = "You healed yourself for " + healAmount + ".";
        } else {
            if (!healerTree.isSpirituallyAttuned()){
                hero.setResource(hero.getResource() - 1);
            }
            updatedHeroHealth = hero.getHealth() + healAmount;
            newMessage = "You healed yourself for " + healAmount + ".";
        }
        hero.setHealth(updatedHeroHealth);
        heroService.updateHero(hero);
        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveHelper.getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getResource(), battleHistory, false);
    }

}
