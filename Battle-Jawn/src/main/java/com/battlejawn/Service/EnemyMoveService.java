package com.battlejawn.Service;

import com.battlejawn.Config.HeroMoveDTO;
import com.battlejawn.EnemyMove.*;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.HeroMove.Heal.Potion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemyMoveService {
    private final HeroService heroService;
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final EnemyService enemyService;
    private final BattleSessionService battleSessionService;
    private HeroMoveDTO heroMoveDTO;

    public EnemyMoveService(BattleSessionService battleSessionService, EnemyService enemyService, HeroService heroService, BattleHistoryMessageService battleHistoryMessageService) {
        this.battleSessionService = battleSessionService;
        this.enemyService = enemyService;
        this.heroService = heroService;
        this.battleHistoryMessageService = battleHistoryMessageService;
    }

    public HeroMoveDTO enemyMove(Long battleSessionId){
        Enemy enemy = enemyService.getEnemyById(battleSessionService.getBattleSessionById(battleSessionId).getEnemyId());
        Hero hero = heroService.getHeroById(battleSessionService.getBattleSessionById(battleSessionId).getHeroId());

        int moveIndex = (int) Math.floor(Math.random() * 9) + 1;
        int damage;

        switch (enemy.getName()) {
            case "Wolf":
                if (moveIndex > 3) {
                    Bite bite = new Bite();
                    damage = bite.attack();
                    HeroMoveDTO enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Bite");
                    return enemyMoveDTO;
                } else {
                    Maim maim = new Maim();
                    damage = maim.attack();
                    HeroMoveDTO enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Maim");
                    return enemyMoveDTO;
                }
            case "Spirit":
                if (moveIndex > 0) {
                    ShadowBlast shadowBlast = new ShadowBlast();
                    damage = shadowBlast.attack();
                    HeroMoveDTO enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Shadow Blast");
                    return enemyMoveDTO;
                } else if (moveIndex == 11) {
                    Paralyze paralyze = new Paralyze();
                    boolean paralyzeSuccess = paralyze.useParalyze();
                } else {
                    SoulEater soulEater = new SoulEater();
                    damage = soulEater.attack();
                    HeroMoveDTO enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Soul Eater");
                    return enemyMoveDTO;
                }
                break;
            case "Thief":
                if (enemy.getHealth() < 40 && moveIndex < 3 && enemy.getPotions() > 0) {
                    HeroMoveDTO enemyMoveDTO = processPotion(enemy, battleSessionId, hero);
                    return enemyMoveDTO;
                } else if (moveIndex > 8 && enemy.getPotions() > 0 && enemy.getHealth() != enemy.getMaxHealth()) {
                    HeroMoveDTO enemyMoveDTO = processPotion(enemy, battleSessionId, hero);
                    return enemyMoveDTO;
                } else if (moveIndex == 1) {
                    Stab stab = new Stab();
                    damage = stab.attack();
                    HeroMoveDTO enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Stab");
                    return enemyMoveDTO;
                } else {
                    HeroMoveDTO enemyMoveDTO = processSteal(enemy, battleSessionId, hero);
                    return enemyMoveDTO;
                }
            case "Orc":
                if (enemy.getHealth() < 40 && moveIndex < 3 && enemy.getPotions() > 0) {
                    HeroMoveDTO enemyMoveDTO = processPotion(enemy, battleSessionId, hero);
                    return enemyMoveDTO;
                } else if (moveIndex > 8 && enemy.getPotions() > 0 && enemy.getHealth() != enemy.getMaxHealth()) {
                    HeroMoveDTO enemyMoveDTO = processPotion(enemy, battleSessionId, hero);
                    return enemyMoveDTO;
                } else if (moveIndex > 4) {
                    Strike strike = new Strike();
                    damage = strike.attack();
                    HeroMoveDTO enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Strike");
                    return enemyMoveDTO;
                } else {
                    Impale impale = new Impale();
                    damage = impale.attack();
                    HeroMoveDTO enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Impale");
                    return enemyMoveDTO;
                }
        }
        return null;
    }

    public HeroMoveDTO processEnemyMove(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move) {
        int updatedHeroHealth;
        String newMessage;
        if (damage > hero.getHealth()) {
            updatedHeroHealth = 0;
            newMessage = getDamageMessage(move, damage);
            heroService.updateHealthById(updatedHeroHealth, hero.getId());
            String heroDefeatedMessage = "You have been defeated by the enemy!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            battleHistoryMessageService.createNewMessage(battleSessionId, heroDefeatedMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getPotions(), battleHistory, true);
        } else {
            updatedHeroHealth = hero.getHealth() - damage;
            newMessage = getDamageMessage(move, damage);
            heroService.updateHealthById(updatedHeroHealth, hero.getId());
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getPotions(), battleHistory, false);
        }
    }

    public HeroMoveDTO processPotion(Enemy enemy, Long battleSessionId, Hero hero) {

            Potion potion = new Potion();
            int updatedEnemyHealth;
            int updatedPotionCount = enemy.getPotions() - 1;
            int healAmount = potion.usePotion();
            if (healAmount > enemy.getMaxHealth()) {
                updatedEnemyHealth = enemy.getMaxHealth();
            } else {
                updatedEnemyHealth = enemy.getHealth() + healAmount;
            }
            enemyService.updatePotionCountById(updatedPotionCount, enemy.getId());
            enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
            String newMessage = "Enemy used a potion.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            return heroMoveDTO = getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getPotions(), battleHistory, false);
    }

    public HeroMoveDTO processSteal(Enemy enemy, Long battleSessionId, Hero hero) {
        if (hero.getPotions() > 0 && enemy.getPotions() < enemy.getMaxPotions()) {
            Steal steal = new Steal();
            boolean stealSuccess = steal.useSteal();
            if (stealSuccess) {
                int updatedHeroPotionCount = hero.getPotions() - 1;
                int updatedEnemyPotionCount = enemy.getPotions() + 1;
                heroService.updatePotionCountById(updatedHeroPotionCount, hero.getId());
                enemyService.updatePotionCountById(updatedEnemyPotionCount, enemy.getId());
                String newMessage = "Enemy stole a potion!";
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), updatedHeroPotionCount, battleHistory, false);
            } else {
                String newMessage = "Enemy tried to steal. They didn't find anything.";
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getPotions(), battleHistory, false);
            }
        } else {
            String newMessage = "Enemy tried to steal, but you are out of potions!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            return heroMoveDTO = getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getPotions(), battleHistory, false);
        }
    }

    public String getDamageMessage(String move, int damage) {
        if (damage > 0) {
            return move + " did " + damage + " damage.";
        } else {
            return move + " missed!";
        }
    }

    public HeroMoveDTO getHeroMoveReturnObject(int enemyHealth, int heroHealth, int potionCount, List<String> battleHistory, boolean gameOver) {
        HeroMoveDTO heroMoveDTO = new HeroMoveDTO();
        heroMoveDTO.setEnemyHealth(enemyHealth);
        heroMoveDTO.setHeroHealth(heroHealth);
        heroMoveDTO.setPotionCount(potionCount);
        heroMoveDTO.setBattleHistory(battleHistory);
        heroMoveDTO.setGameOver(gameOver);
        return heroMoveDTO;
    }

}
