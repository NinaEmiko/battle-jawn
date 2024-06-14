package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Inventory;
import com.battlejawn.HeroMove.Attack.*;
import com.battlejawn.HeroMove.Heal.*;
import com.battlejawn.HeroMove.Run;
import com.battlejawn.HeroMove.Steal;
import com.battlejawn.HeroMove.StrongAttack.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class HeroMoveService {
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final Stab stab;
    private final Strike strike;
    private final Wand wand;
    private final Run run;
    private final Steal steal;
    private final Potion potion;
    private final InventoryService inventoryService;
    private final BattleStatusService battleStatusService;
    private final Logger logger = Logger.getLogger(HeroMoveService.class.getName());

    @Transactional
    public HeroMoveDTO heroMove(String move, Long battleSessionId) {
        logger.info("Inside heroMove service method. Move: " + move + ". Battle Session ID: " + battleSessionId);

        HeroMoveDTO heroMoveDTO;
        int damage;
        int healAmount;

        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());

        switch (move) {
            case "Wand":
                damage = wand.attack();
                heroMoveDTO = processHeroAttack(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Strike":
                damage = strike.attack();
                heroMoveDTO = processHeroAttack(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Stab":
                damage = stab.attack();
                if (damage == 0) {
                    hero.setResource(0);
                    heroService.updateHero(hero);
                }
                heroMoveDTO = processHeroAttack(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "FireBlast":
                FireBlast fireBlast = new FireBlast();
                damage = fireBlast.attack();
                int resourceDamage = damage + (hero.getResource() * 5);
                hero.setResource(0);
                heroService.updateHero(hero);
                heroMoveDTO = processHeroAttack(resourceDamage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "IceBlast":
                IceBlast iceBlast = new IceBlast();
                damage = iceBlast.attack();
                heroMoveDTO = processHeroAttack(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Holy":
                Holy holy = new Holy();
                damage = holy.attack();
                heroMoveDTO = processHeroAttack(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Impale":
                Impale impale = new Impale();
                damage = impale.attack();
                heroMoveDTO = processHeroAttack(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "BackStab":
                BackStab backStab = new BackStab();
                damage = backStab.attack();
                heroMoveDTO = processHeroAttack(damage, enemy, battleSessionId, hero, move);
                return heroMoveDTO;
            case "Heal":
                Heal heal = new Heal();
                healAmount = heal.useHeal();
                heroMoveDTO = processHeroHeal(healAmount, enemy, battleSessionId, hero);
                return heroMoveDTO;
            case "Steal":
                heroMoveDTO = processSteal(enemy, battleSessionId, hero);
                return heroMoveDTO;
            case "Block":
                heroMoveDTO = processBlock(battleSessionId, enemy, hero);
                return heroMoveDTO;
            case "Potion":
                heroMoveDTO = processPotion(enemy, battleSessionId, hero);
                return heroMoveDTO;
            case "Water":
                heroMoveDTO = processWater(enemy, hero, battleSessionId);
                return heroMoveDTO;
            default:
                heroMoveDTO = processRun(enemy, battleSessionId, hero);
                return heroMoveDTO;
        }
    }

    public HeroMoveDTO processHeroAttack(int damage, Enemy enemy, Long battleSessionId, Hero hero, String move) {
        int updatedEnemyHealth;
        String newMessage;

        boolean enoughResource = processHeroResource(move, hero);

        if (!enoughResource) {
            updatedEnemyHealth = enemy.getHealth();
            newMessage = switch (hero.getRole()) {
                case "Tank" -> "You do not have enough power.";
                case "Healer" -> "You do not have enough spirit.";
                case "Caster" -> "You do not have enough magic.";
                default -> "You do not have enough energy.";
            };
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, false);
        } else if (damage > enemy.getHealth()) {
            updatedEnemyHealth = 0;
            if (damage > 0 && move.equals("Wand") || move.equals("Strike") || move.equals("Stab")){
                if (hero.getResource() != hero.getMaxResource()) {
                    hero.setResource(hero.getResource() + 1);
                    heroService.updateHero(hero);
                }
            }
            newMessage = getDamageMessage(move, damage);
            enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
            String enemyDefeatedMessage = "You have defeated the enemy!";
            hero.setWinCount(hero.getWinCount() + 1);
            heroService.updateHero(hero);
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            battleHistoryMessageService.createNewMessage(battleSessionId, enemyDefeatedMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, true);
        } else {
            updatedEnemyHealth = enemy.getHealth() - damage;
            if (damage > 0 && move.equals("Wand") || move.equals("Strike") || move.equals("Stab")){
                if (hero.getResource() != hero.getMaxResource()) {
                    hero.setResource(hero.getResource() + 1);
                    heroService.updateHero(hero);
                }
            }
            newMessage = getDamageMessage(move, damage);
            enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(updatedEnemyHealth, hero.getHealth(), hero.getResource(), battleHistory, false);
        }
    }

public String getDamageMessage(String move, int damage) {
    if (damage > 0) {
        return move + " did " + damage + " damage.";
    } else {
        return move + " missed!";
    }
}

    public HeroMoveDTO processHeroHeal(int healAmount, Enemy enemy, Long battleSessionId, Hero hero) {
        int updatedHeroHealth;
        String newMessage;
        if (hero.getResource() == 0) {
            newMessage = "You do not have enough spirit.";
            updatedHeroHealth = hero.getHealth();
        } else if (hero.getResource() > 0 && healAmount + hero.getHealth() > hero.getMaxHealth()) {
            updatedHeroHealth = hero.getMaxHealth();
            hero.setResource(hero.getResource() - 1);
            newMessage = "You healed yourself for " + healAmount + ".";
        } else {
            hero.setResource(hero.getResource() - 1);
            updatedHeroHealth = hero.getHealth() + healAmount;
            newMessage = "You healed yourself for " + healAmount + ".";
        }
        hero.setHealth(updatedHeroHealth);
        heroService.updateHero(hero);
        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getResource(), battleHistory, false);
    }
    public HeroMoveDTO processWater(Enemy enemy, Hero hero, Long battleSessionId) {
        Inventory inventory = hero.getInventory();
        int waterCount = inventoryService.findItemCount(inventory, "Water");

        String newMessage;
        if (waterCount > 0 && hero.getResource() == hero.getMaxResource()){
            newMessage = switch(hero.getRole()){
                case "Tank" -> "Your power is maxed out.";
                case "Healer" -> "Your spirit is maxed out.";
                case "Caster" -> "Your magic is maxed out.";
                default -> "Your energy is maxed out.";
            };
        } else if (waterCount > 0 && hero.getResource() != hero.getMaxResource()) {
            newMessage = switch (hero.getRole()) {
                case "Tank" -> "You feel empowered.";
                case "Healer" -> "Your spirit has risen.";
                case "Caster" -> "Your magic has risen.";
                default -> "Your energy has increased.";
            };
            hero.setResource(hero.getMaxResource());
            heroService.updateHero(hero);
        } else {
            newMessage = "You are out of water!";
        }

        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
    }
    public HeroMoveDTO processBlock(Long battleSessionId, Enemy enemy, Hero hero) {
        String newMessage;
        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        BattleStatus battleStatus = battleSession.getBattleStatus();

        if (hero.getResource() < 1) {
            newMessage = "You do not have enough power.";
        } else {
            battleStatus.setHeroBlocking(true);
            battleStatusService.saveBattleStatus(battleStatus);
            newMessage = "You prepared to block.";
            hero.setResource(hero.getResource() - 1);
            heroService.updateHero(hero);
        }

        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
        List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
    }

    public HeroMoveDTO processPotion(Enemy enemy, Long battleSessionId, Hero hero) {
        Inventory inventory = hero.getInventory();
        int potionCount = inventoryService.findItemCount(inventory, "Potion");

        if (potionCount > 0 && hero.getHealth() != hero.getMaxHealth()) {
            int updatedHeroHealth;
            inventoryService.removeFirstFromInventory(hero.getId(), "Potion");
            int healAmount = potion.usePotion();
            if (healAmount + hero.getHealth() > hero.getMaxHealth()) {
                updatedHeroHealth = hero.getMaxHealth();
            } else {
                updatedHeroHealth = hero.getHealth() + healAmount;
            }
            hero.setHealth(updatedHeroHealth);
            heroService.updateHero(hero);
            String newMessage = "You feel better now.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(enemy.getHealth(), updatedHeroHealth, hero.getResource(), battleHistory, false);
        } else if (potionCount > 0 && hero.getHealth() == hero.getMaxHealth()) {
            String newMessage = "You are at full health.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
        } else {
            String newMessage = "You are out of potions!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
        }
    }

    public HeroMoveDTO processSteal(Enemy enemy, Long battleSessionId, Hero hero) {
        Inventory inventory = hero.getInventory();
        int emptySpaces = inventoryService.getEmptySlotSize(hero.getId());

        if (enemy.getPotions() > 0 && emptySpaces > 0) {
            boolean stealSuccess = steal.useSteal();
            if (stealSuccess) {
                inventoryService.addToFirstEmptySlot(inventory, "Potion");
                int updatedEnemyPotionCount = enemy.getPotions() - 1;
                if (hero.getResource() > 0) {
                    hero.setResource(3);
                } else {
                    hero.setResource(2);
                }
                heroService.updateHero(hero);
                enemyService.updatePotionCountById(updatedEnemyPotionCount, enemy.getId());
                String newMessage = "You stole a potion!";
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
                return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
            } else {
                String newMessage = "You didn't find anything.";
                battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
                List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
                return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
            }
        } else {
            String newMessage = "You didn't find anything.";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
        }
    }

    public HeroMoveDTO processRun(Enemy enemy, Long battleSessionId, Hero hero) {
        boolean gameOver = run.useRun();
        if (gameOver) {
            hero.setRunCount(hero.getRunCount() + 1);
            heroService.updateHero(hero);
            String newMessage = "You successfully ran away!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, true);
        } else {
            String newMessage = "You could not get away!";
            battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
            List<String> battleHistory = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
            return getHeroMoveReturnObject(enemy.getHealth(), hero.getHealth(), hero.getResource(), battleHistory, false);
        }
    }

    public boolean processHeroResource(String move, Hero hero) {
        switch (move){
            case "IceBlast", "Block":
                if (hero.getResource() < 1) {
                    return false;
                } else {
                    hero.setResource(hero.getResource() - 1);
                    heroService.updateHero(hero);
                    return true;
                }
            case "Holy":
                if (hero.getResource() < 2) {
                    return false;
                } else {
                    hero.setResource(hero.getResource() - 2);
                    heroService.updateHero(hero);
                    return true;
                }
            case "Impale", "BackStab":
                if (hero.getResource() < 3) {
                    return false;
                } else {
                    hero.setResource(hero.getResource() - 3);
                    heroService.updateHero(hero);
                    return true;
                }
            default:
                return true;
        }
    }

    public HeroMoveDTO getHeroMoveReturnObject(int enemyHealth, int heroHealth, int heroResource, List<String> battleHistory, boolean gameOver) {
        HeroMoveDTO heroMoveDTO = new HeroMoveDTO();
        heroMoveDTO.setEnemyHealth(enemyHealth);
        heroMoveDTO.setHeroHealth(heroHealth);
        heroMoveDTO.setHeroResource(heroResource);
        heroMoveDTO.setBattleHistory(battleHistory);
        heroMoveDTO.setGameOver(gameOver);
        return heroMoveDTO;
    }

}
