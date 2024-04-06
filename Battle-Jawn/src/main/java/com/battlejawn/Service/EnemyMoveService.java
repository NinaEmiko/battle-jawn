package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.EnemyMove.*;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.HeroMove.Heal.Potion;
import com.battlejawn.Randomizer.Randomizer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class EnemyMoveService {
    private final HeroService heroService;
    private final Potion potion;
    private final EnemySteal enemySteal;
    private final Randomizer randomizer;
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final EnemyService enemyService;
    private final BattleSessionService battleSessionService;
    private final Logger logger = Logger.getLogger(EnemyMoveService.class.getName());

    public HeroMoveDTO enemyMove(Long battleSessionId){
        logger.info("Inside enemyMove service class. Battle Session Id: " + battleSessionId + ".");

        Enemy enemy = enemyService.getEnemyById(battleSessionService.getBattleSessionById(battleSessionId).getEnemyId());
        Hero hero = heroService.getHeroById(battleSessionService.getBattleSessionById(battleSessionId).getHeroId());

        int moveIndex = randomizer.getRandomInt(9);
        HeroMoveDTO enemyMoveDTO;
        int damage;

        return switch (enemy.getName()) {
            case "Wolf" -> {
                if (moveIndex > 3) {
                    Bite bite = new Bite();
                    damage = bite.attack();
                    enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Bite");
                } else {
                    Maim maim = new Maim();
                    damage = maim.attack();
                    enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Maim");
                }
                yield enemyMoveDTO;
            }
            case "Spirit" -> {
                if (moveIndex > 0) {
                    ShadowBlast shadowBlast = new ShadowBlast();
                    damage = shadowBlast.attack();
                    enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Shadow Blast");
                } else {
                    SoulEater soulEater = new SoulEater();
                    damage = soulEater.attack();
                    enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Soul Eater");
                }
                yield enemyMoveDTO;
            }
            case "Thief" -> {
                if (enemy.getHealth() < 30 && moveIndex < 3 && enemy.getPotions() > 0) {
                    enemyMoveDTO = processPotion(enemy, battleSessionId, hero);
                } else if (moveIndex > 9 && enemy.getPotions() > 0 && enemy.getHealth() != enemy.getMaxHealth()) {
                    enemyMoveDTO = processPotion(enemy, battleSessionId, hero);
                } else if (moveIndex > 1) {
                    Stab stab = new Stab();
                    damage = stab.attack();
                    enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Stab");
                } else {
                    enemyMoveDTO = processEnemySteal(enemy, battleSessionId, hero);
                }
                yield enemyMoveDTO;
            }
            case "Orc" -> {
                if (enemy.getHealth() < 40 && moveIndex < 3 && enemy.getPotions() > 0) {
                    enemyMoveDTO = processPotion(enemy, battleSessionId, hero);
                } else if (moveIndex > 8 && enemy.getPotions() > 0 && enemy.getHealth() != enemy.getMaxHealth()) {
                    enemyMoveDTO = processPotion(enemy, battleSessionId, hero);
                } else if (moveIndex > 4) {
                    Strike strike = new Strike();
                    damage = strike.attack();
                    enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Strike");
                } else {
                    Impale impale = new Impale();
                    damage = impale.attack();
                    enemyMoveDTO = processEnemyMove(damage, enemy, battleSessionId, hero, "Impale");
                }
                yield enemyMoveDTO;
            }
            default -> null;
        };
    }

    public HeroMoveDTO processEnemyMove(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move) {
        int updatedHeroHealth;
        String newMessage;
        if (damage > hero.getHealth()) {
            updatedHeroHealth = 0;
            newMessage = getDamageMessage(move, damage);
            heroService.updateLossCountById(hero.getId(), hero.getLossCount() + 1);
            heroService.updateHealthById(updatedHeroHealth, hero.getId());
            String heroDefeatedMessage = "You have been defeated by the enemy!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            battleHistoryMessageService.createNewMessage(battleSessionId, heroDefeatedMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getPotions(), battleHistory, true);
        } else {
            updatedHeroHealth = hero.getHealth() - damage;
            newMessage = getDamageMessage(move, damage);
            heroService.updateHealthById(updatedHeroHealth, hero.getId());
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getPotions(), battleHistory, false);
        }
    }

    public HeroMoveDTO processPotion(Enemy enemy, Long battleSessionId, Hero hero) {

            int updatedEnemyHealth;
            int updatedPotionCount = enemy.getPotions() - 1;
            int healAmount = potion.usePotion();
            if (healAmount + enemy.getHealth() > enemy.getMaxHealth()) {
                updatedEnemyHealth = enemy.getMaxHealth();
            } else {
                updatedEnemyHealth = enemy.getHealth() + healAmount;
            }
            enemyService.updatePotionCountById(updatedPotionCount, enemy.getId());
            enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
            String newMessage = "Enemy used a potion.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getPotions(), battleHistory, false);
    }

    public HeroMoveDTO processEnemySteal(Enemy enemy, Long battleSessionId, Hero hero) {
        logger.info("Inside processEnemySteal move method.");
        if (hero.getPotions() > 0 && enemy.getPotions() < enemy.getMaxPotions()) {
            logger.info("Inside first if statement. Hero potion count: " + hero.getPotions() + ". Enemy potion count: " + enemy.getPotions() + ". Enemy potion capacity: " + enemy.getMaxPotions());
            boolean stealSuccess = enemySteal.useSteal();
            logger.info("stealSuccess: " + stealSuccess);
            if (stealSuccess) {
                int updatedHeroPotionCount = hero.getPotions() - 1;
                int updatedEnemyPotionCount = enemy.getPotions() + 1;
                heroService.updatePotionCountById(updatedHeroPotionCount, hero.getId());
                enemyService.updatePotionCountById(updatedEnemyPotionCount, enemy.getId());
                String newMessage = "Enemy stole a potion!";
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
                return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), updatedHeroPotionCount, battleHistory, false);
            } else {
                String newMessage = "Enemy tried to steal. They didn't find anything.";
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
                return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getPotions(), battleHistory, false);
            }
        } else {
            logger.info("Inside first else statement. Hero potion count: " + hero.getPotions() + ". Enemy potion count: " + enemy.getPotions() + ". Enemy potion capacity: " + enemy.getMaxPotions());

            String newMessage = "Enemy tried to steal, but you are out of potions!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getPotions(), battleHistory, false);
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
