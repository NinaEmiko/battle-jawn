package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.HeroMove.Attack.Stab;
import com.battlejawn.HeroMove.Attack.Strike;
import com.battlejawn.HeroMove.Attack.Wand;
import com.battlejawn.HeroMove.Heal.Heal;
import com.battlejawn.HeroMove.StrongAttack.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class HeroMoveService {
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private final Logger logger = Logger.getLogger(HeroMoveService.class.getName());

    public HeroMoveService(BattleSessionService battleSessionService, HeroService heroService, EnemyService enemyService) {
        this.battleSessionService = battleSessionService;
        this.heroService = heroService;
        this.enemyService = enemyService;
    }

    @Transactional
    public void heroMove(String move, Long battleSessionId) {
        logger.info("Inside heroMove Service method. Move: " + move + ". Battle Session ID: " + battleSessionId);

        int damage;
        int healAmount;
        String newMessage;
        int updatedEnemyHealth;
        int updatedHeroHealth;

        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());
        ArrayList<String> battleHistory = battleSession.getBattleHistory();

        switch (move) {
            case "Wand":    Wand wand = new Wand();
                damage = wand.attack();
                if (damage > enemy.getHealth()) {
                    updatedEnemyHealth = 0;
                } else {
                    updatedEnemyHealth = enemy.getHealth() - damage;
                }
                enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
//                newMessage = newMessageGenerator("Wand", damage);
//                battleHistory.add(newMessage);
//                battleSessionService.addMessageToBattleHistory(battleHistory, battleSessionId);
                break;
            case "Strike":  Strike strike = new Strike();
                damage = strike.attack();
                if (damage > enemy.getHealth()) {
                    updatedEnemyHealth = 0;
                } else {
                    updatedEnemyHealth = enemy.getHealth() - damage;
                }
                enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
//                newMessage = newMessageGenerator("Strike", damage);
//                battleHistory.add(newMessage);
//                battleSessionService.addMessageToBattleHistory(battleHistory, battleSessionId);
                break;
            case "Stab":    Stab stab = new Stab();
                damage = stab.attack();
                if (damage > enemy.getHealth()) {
                    updatedEnemyHealth = 0;
                } else {
                    updatedEnemyHealth = enemy.getHealth() - damage;
                }
                enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
//                newMessage = newMessageGenerator("Stab", damage);
//                battleHistory.add(newMessage);
//                battleSessionService.addMessageToBattleHistory(battleHistory, battleSessionId);
                break;
            case "Blast":       Blast blast = new Blast();
                damage = blast.attack();
                //Change to EnemyRepository method that updates enemy health in database
                if (damage > enemy.getHealth()) {
                    updatedEnemyHealth = 0;
                } else {
                    updatedEnemyHealth = enemy.getHealth() - damage;
                }
                enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
//                newMessage = newMessageGenerator("Blast", damage);
//                battleHistory.add(newMessage);
//                battleSessionService.addMessageToBattleHistory(battleHistory, battleSessionId);
                break;
            case "Holy":        Holy holy = new Holy();
                damage = holy.attack();
                if (damage > enemy.getHealth()) {
                    updatedEnemyHealth = 0;
                } else {
                    updatedEnemyHealth = enemy.getHealth() - damage;
                }
                enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
//                newMessage = newMessageGenerator("Holy", damage);
//                battleHistory.add(newMessage);
//                battleSessionService.addMessageToBattleHistory(battleHistory, battleSessionId);
                break;
            case "Impale":      Impale impale = new Impale();
                damage = impale.attack();
                if (damage > enemy.getHealth()) {
                    updatedEnemyHealth = 0;
                } else {
                    updatedEnemyHealth = enemy.getHealth() - damage;
                }
                enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
//                newMessage = newMessageGenerator("Impale", damage);
//                battleHistory.add(newMessage);
//                battleSessionService.addMessageToBattleHistory(battleHistory, battleSessionId);
                break;
            case "Backstab":    BackStab backStab = new BackStab();
                damage = backStab.attack();
                if (damage > enemy.getHealth()) {
                    updatedEnemyHealth = 0;
                } else {
                    updatedEnemyHealth = enemy.getHealth() - damage;
                }
                enemyService.updateHealthById(updatedEnemyHealth, enemy.getId());
//                newMessage = newMessageGenerator("Backstab", damage);
//                battleHistory.add(newMessage);
//                battleSessionService.addMessageToBattleHistory(battleHistory, battleSessionId);
                break;
            case "Heal":    Heal heal = new Heal();
                healAmount = heal.useHeal();
                if (healAmount > hero.getMaxHealth()) {
                    updatedHeroHealth = hero.getMaxHealth();
                } else {
                    updatedHeroHealth = hero.getHealth() + healAmount;
                }
                heroService.updateHealthById(updatedHeroHealth, hero.getId());
//                newMessage = newMessageGenerator("Backstab", damage);
//                battleHistory.add(newMessage);
//                battleSessionService.addMessageToBattleHistory(battleHistory, battleSessionId);
                break;

        }
    }

    public String newMessageGenerator(String name, int damage) {
        if (damage > 0) {
            return name + " did " + damage + " damage!";
        } else {
            return name + " missed!";
        }
    }

}
