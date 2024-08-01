package com.battlejawn.Service;

import com.battlejawn.DTO.EnemyMoveDTO;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.EnemyMove.*;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.Entities.TalentTree.DPSTree;
import com.battlejawn.Entities.TalentTree.TankTree;
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
    private final InventoryService inventoryService;
    private final BattleStatusService battleStatusService;
    private final Logger logger = Logger.getLogger(EnemyMoveService.class.getName());

    public EnemyMoveDTO enemyMove(Long battleSessionId){
        logger.info("Inside enemyMove service class. Battle Session Id: " + battleSessionId + ".");

        Enemy enemy = enemyService.getEnemyById(battleSessionService.getBattleSessionById(battleSessionId).getEnemyId());
        Hero hero = heroService.getHeroById(battleSessionService.getBattleSessionById(battleSessionId).getHeroId());

        int moveIndex = randomizer.getRandomInt(9);
        EnemyMoveDTO enemyMoveDTO;
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

    public EnemyMoveDTO processEnemyMove(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move) {
        int updatedHeroHealth;
        String newMessage;
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        BattleStatus battleStatus = battleSession.getBattleStatus();

        if (battleStatus.isHeroBlocking()) {
            TankTree tankTree = (TankTree) hero.getTalentTree();
            hero.setResource(hero.getMaxResource());
            if (tankTree.isImprovedBlock1()){
                hero.setHealth(hero.getHealth() + 5);
            }
            if (tankTree.isImprovedBlock2()){
                hero.setHealth(hero.getHealth() + 5);
            }
            heroService.updateHero(hero);
            battleStatus.setHeroBlocking(false);
            battleStatusService.saveBattleStatus(battleStatus);
            newMessage = enemy.getName() + " tried to use " + move + ", but " + hero.getName() + " blocked it.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getEnemyMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
        } else if (damage > hero.getHealth()) {
            updatedHeroHealth = 0;
            newMessage = getDamageMessage(move, damage);
            hero.setLossCount(hero.getLossCount() + 1);
            hero.setHealth(updatedHeroHealth);
            heroService.updateHero(hero);
            String heroDefeatedMessage = "You have been defeated by the enemy!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            battleHistoryMessageService.createNewMessage(battleSessionId, heroDefeatedMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getEnemyMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getResource(), battleHistory, true);
        } else {
            updatedHeroHealth = hero.getHealth() - damage;
            newMessage = getDamageMessage(move, damage);
            hero.setHealth(updatedHeroHealth);
            heroService.updateHero(hero);
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getEnemyMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getResource(), battleHistory, false);
        }
    }

    public EnemyMoveDTO processPotion(Enemy enemy, Long battleSessionId, Hero hero) {

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
            return getEnemyMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, false);
    }

    public EnemyMoveDTO processEnemySteal(Enemy enemy, Long battleSessionId, Hero hero) {
        Inventory inventory = hero.getInventory();
        int potionCount = inventoryService.findItemCount(inventory, "Potion");

        logger.info("Inside processEnemySteal move method.");
        if (potionCount > 0 && enemy.getPotions() < enemy.getMaxPotions()) {
            DPSTree dpsTree = (DPSTree) hero.getTalentTree();
            logger.info("Inside first if statement. Hero potion count: " + potionCount + ". Enemy potion count: " + enemy.getPotions() + ". Enemy potion capacity: " + enemy.getMaxPotions());
            boolean stealSuccess = enemySteal.useSteal();
            if (dpsTree.isHonorAmongThieves()){
                stealSuccess = false;
            }
            logger.info("stealSuccess: " + stealSuccess);
            if (stealSuccess) {
                inventoryService.removeFirstFromInventory(hero.getId(), "potion");
                int updatedEnemyPotionCount = enemy.getPotions() + 1;
                heroService.updateHero(hero);
                enemyService.updatePotionCountById(updatedEnemyPotionCount, enemy.getId());
                String newMessage = "Enemy stole a potion!";
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
                return getEnemyMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
            } else {
                String newMessage = "Enemy tried to steal. They didn't find anything.";
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
                return getEnemyMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
            }
        } else {
            logger.info("Inside first else statement. Enemy potion count: " + enemy.getPotions() + ". Enemy potion capacity: " + enemy.getMaxPotions());

            String newMessage = "Enemy tried to steal, but you are out of potions!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getEnemyMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
        }
    }

    public String getDamageMessage(String move, int damage) {
        if (damage > 0) {
            return move + " did " + damage + " damage.";
        } else {
            return move + " missed!";
        }
    }

    public EnemyMoveDTO getEnemyMoveReturnObject(int enemyHealth, int heroHealth, int heroResource, List<String> battleHistory, boolean gameOver) {
        EnemyMoveDTO enemyMoveDTO = new EnemyMoveDTO();
        enemyMoveDTO.setEnemyHealth(enemyHealth);
        enemyMoveDTO.setHeroHealth(heroHealth);
        enemyMoveDTO.setHeroResource(heroResource);
        enemyMoveDTO.setBattleHistory(battleHistory);
        enemyMoveDTO.setGameOver(gameOver);
        return enemyMoveDTO;
    }

}
