package com.battlejawn.Service;

import com.battlejawn.Config.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.HeroMove.Attack.Stab;
import com.battlejawn.HeroMove.Attack.Strike;
import com.battlejawn.HeroMove.Attack.Wand;
import com.battlejawn.HeroMove.Heal.Heal;
import com.battlejawn.HeroMove.Heal.Potion;
import com.battlejawn.HeroMove.StrongAttack.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class HeroMoveService {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final Logger logger = Logger.getLogger(HeroMoveService.class.getName());
    private HeroMoveDTO heroMoveDTO;

    public HeroMoveService(BattleHistoryMessageService battleHistoryMessageService, BattleSessionService battleSessionService, HeroService heroService, EnemyService enemyService) {
        this.battleHistoryMessageService = battleHistoryMessageService;
        this.battleSessionService = battleSessionService;
        this.heroService = heroService;
        this.enemyService = enemyService;
    }

    @Transactional
    public HeroMoveDTO heroMove(String move, Long battleSessionId) {
        logger.info("Inside heroMove service method. Move: " + move + ". Battle Session ID: " + battleSessionId);

        int damage;
        int healAmount;
        String newMessage;
        int updatedHeroHealth;
        HeroMoveDTO heroMoveDTO;

        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());

        switch (move) {
            case "Wand":    Wand wand = new Wand();
                damage = wand.attack();
                heroMoveDTO = processHeroMove(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Strike":  Strike strike = new Strike();
                damage = strike.attack();
                heroMoveDTO = processHeroMove(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Stab":    Stab stab = new Stab();
                damage = stab.attack();
                heroMoveDTO = processHeroMove(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Blast":       Blast blast = new Blast();
                damage = blast.attack();
                heroMoveDTO = processHeroMove(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Holy":        Holy holy = new Holy();
                damage = holy.attack();
                heroMoveDTO = processHeroMove(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Impale":      Impale impale = new Impale();
                damage = impale.attack();
                heroMoveDTO = processHeroMove(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Backstab":    BackStab backStab = new BackStab();
                damage = backStab.attack();
                heroMoveDTO = processHeroMove(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Heal":    Heal heal = new Heal();
                healAmount = heal.useHeal();
                heroMoveDTO = processHeroHeal(healAmount, enemy, battleSessionId, hero);
                return heroMoveDTO;
            case "Potion":
                if (hero.getPotions() > 0) {
                    heroMoveDTO = processPotion(enemy, battleSessionId, hero);
                    return heroMoveDTO;
                } else {
                    //Update battleHistory
                }
                break;
            case "Steal":
                if (enemy.getPotions() > 0 && hero.getPotions() < hero.getMaxPotions()) {
                    heroMoveDTO = processSteal(enemy, battleSessionId, hero);
                    return heroMoveDTO;
                } else {
                    //Update battleHistory
                }
                break;
        }
        return null;
    }

    public HeroMoveDTO processHeroMove(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move) {
        int updatedEnemyHealth;
        if (damage > enemy.getHealth()) {
            updatedEnemyHealth = 0;
        } else {
            updatedEnemyHealth = enemy.getHealth() - damage;
        }
        enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
        String newMessage;
        if (damage > 0) {
            newMessage = move + " did " + damage + " damage!";
        } else {
            newMessage = move + " missed!";
        }
        BattleHistoryMessage battleHistoryMessage = battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveDTO = getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getPotions(), battleHistory);
    }

    public HeroMoveDTO processHeroHeal(int healAmount, Enemy enemy, Long battleSessionId, Hero hero) {
        int updatedHeroHealth;
        if (healAmount > hero.getMaxHealth()) {
            updatedHeroHealth = hero.getMaxHealth();
        } else {
            updatedHeroHealth = hero.getHealth() + healAmount;
        }
        heroService.updateHealthById(updatedHeroHealth, hero.getId());
        String newMessage = "You healed yourself for " + healAmount;
        BattleHistoryMessage battleHistoryMessage = battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getPotions(), battleHistory);
    }

    public HeroMoveDTO processPotion(Enemy enemy, Long battleSessionId, Hero hero) {
        Potion potion = new Potion();
        int updatedHeroHealth;
        int updatedPotionCount = hero.getPotions() - 1;
        int healAmount = potion.usePotion();
        if (healAmount > hero.getMaxHealth()) {
            updatedHeroHealth = hero.getMaxHealth();
        } else {
            updatedHeroHealth = hero.getHealth() + healAmount;
        }
        heroService.updatePotionCountById(updatedPotionCount, hero.getId());
        heroService.updateHealthById(updatedHeroHealth, hero.getId());
        String newMessage = "You feel better now";
        BattleHistoryMessage battleHistoryMessage = battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getPotions(), battleHistory);
    }

    public HeroMoveDTO processSteal(Enemy enemy, Long battleSessionId, Hero hero) {
        int updatedPotionCount = hero.getPotions() + 1;
        heroService.updatePotionCountById(updatedPotionCount, hero.getId());
        String newMessage = "You stole a potion!";
        BattleHistoryMessage battleHistoryMessage = battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getPotions(), battleHistory);
    }

    public HeroMoveDTO getHeroMoveReturnObject(int enemyHealth, int heroHealth, int potionCount, List<String> battleHistory) {
        HeroMoveDTO heroMoveDTO = new HeroMoveDTO();
        heroMoveDTO.setEnemyHealth(enemyHealth);
        heroMoveDTO.setHeroHealth(heroHealth);
        heroMoveDTO.setPotionCount(potionCount);
        heroMoveDTO.setBattleHistory(battleHistory);
        return heroMoveDTO;
    }

}
