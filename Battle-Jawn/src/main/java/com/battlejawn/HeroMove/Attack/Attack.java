package com.battlejawn.HeroMove.Attack;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Repository.BattleSessionRepository;
import com.battlejawn.Repository.EnemyRepository;
import com.battlejawn.Repository.HeroRepository;
import com.battlejawn.Service.HeroMoveService;

import java.util.logging.Logger;

public class Attack {

    private int damage;
    private String role;
    private String newMessage;
    private final Logger logger = Logger.getLogger(Attack.class.getName());

    public void useAttack(Hero hero, Enemy enemy, BattleSession battleSession) {
        logger.info("Inside useAttack method. Hero: " + hero + ". Enemy: " + enemy + ". Battle Session: " + battleSession + ".");
        role = hero.getRole();
        int enemyCurrentHealth = enemy.getHealth();

        switch (role) {
            case "Caster", "Healer":  Wand wand = new Wand();
                            damage = wand.attack();
                            newMessage = newMessageGenerator("Wand", damage);
                            battleSession.addNewMessage(newMessage);
                            break;
            case "Tank":    Strike strike = new Strike();
                            damage = strike.attack();
                            newMessage = newMessageGenerator("Strike", damage);
                            battleSession.addNewMessage(newMessage);
                            break;
            case "DPS":     Stab stab = new Stab();
                            damage = stab.attack();
                            newMessage = newMessageGenerator("Stab", damage);
                            battleSession.addNewMessage(newMessage);
                            break;
        }

        enemy.setHealth(enemyCurrentHealth - damage);
    }

    public String newMessageGenerator(String name, int damage) {
        if (damage > 0) {
            return name + " did " + damage + " damage!"; 
        } else { 
            return name + " missed!";
            }
        }
}
