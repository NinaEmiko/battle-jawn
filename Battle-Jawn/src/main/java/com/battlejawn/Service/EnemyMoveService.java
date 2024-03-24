package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.EnemyMove.*;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.HeroMove.Heal.Potion;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
public class EnemyMoveService {
    private final HeroService heroService;
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final EnemyService enemyService;
    private final BattleSessionService battleSessionService;
    private final Logger logger = Logger.getLogger(EnemyMoveService.class.getName());

    public EnemyMoveService(BattleSessionService battleSessionService, EnemyService enemyService, HeroService heroService, BattleHistoryMessageService battleHistoryMessageService) {
        this.battleSessionService = battleSessionService;
        this.enemyService = enemyService;
        this.heroService = heroService;
        this.battleHistoryMessageService = battleHistoryMessageService;
    }

    public HeroMoveDTO enemyMove(Long battleSessionId){
        logger.info("Inside enemyMove service class. Battle Session Id: " + battleSessionId + ".");
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());
        int moveIndex = getRandomIndex();
        int damage;

        return switch (enemy.getName()) {
            case "Wolf" -> {
                if (moveIndex > 3) {
                    Bite bite = new Bite();
                    yield processEnemyMove(bite.attack(), enemy, battleSessionId, hero, "Bite");
                } else {
                    Maim maim = new Maim();
                    yield processEnemyMove(maim.attack(), enemy, battleSessionId, hero, "Maim");
                }
            }
            case "Spirit" -> {
                if (moveIndex > 0) {
                    ShadowBlast shadowBlast = new ShadowBlast();
                    yield processEnemyMove(shadowBlast.attack(), enemy, battleSessionId, hero, "Shadow Blast");
                } else {
                    SoulEater soulEater = new SoulEater();
                    yield processEnemyMove(soulEater.attack(), enemy, battleSessionId, hero, "Soul Eater");
                }
            }
            case "Thief" -> {
                if (enemy.getHealth() < 30 && moveIndex < 3 && enemy.getPotions() > 0) {
                    yield processPotion(enemy, battleSessionId, hero);
                } else if (moveIndex > 9 && enemy.getPotions() > 0 && enemy.getHealth() != enemy.getMaxHealth()) {
                    yield processPotion(enemy, battleSessionId, hero);
                } else if (moveIndex > 1) {
                    Stab stab = new Stab();
                    yield processEnemyMove(stab.attack(), enemy, battleSessionId, hero, "Stab");
                } else {
                    yield processSteal(enemy, battleSessionId, hero);
                }
            }
            case "Orc" -> {
                if (enemy.getHealth() < 40 && moveIndex < 3 && enemy.getPotions() > 0) {
                    yield processPotion(enemy, battleSessionId, hero);
                } else if (moveIndex > 8 && enemy.getPotions() > 0 && enemy.getHealth() != enemy.getMaxHealth()) {
                    yield processPotion(enemy, battleSessionId, hero);
                } else if (moveIndex > 4) {
                    Strike strike = new Strike();
                    yield processEnemyMove(strike.attack(), enemy, battleSessionId, hero, "Strike");
                } else {
                    Impale impale = new Impale();
                    yield processEnemyMove(impale.attack(), enemy, battleSessionId, hero, "Impale");
                }
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
            return getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getPotions(), battleHistory, false);
    }

    public HeroMoveDTO processSteal(Enemy enemy, Long battleSessionId, Hero hero) {
        logger.info("Inside processSteal move method.");
        if (hero.getPotions() > 0 && enemy.getPotions() < enemy.getMaxPotions()) {
            logger.info("Inside first if statement. Hero potion count: " + hero.getPotions() + ". Enemy potion count: " + enemy.getPotions() + ". Enemy potion capacity: " + enemy.getMaxPotions());
            Steal steal = new Steal();
            boolean stealSuccess = steal.useSteal();
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

    public int getRandomIndex(){
        return (int) Math.floor(Math.random() * 9) + 1;
    }

}
