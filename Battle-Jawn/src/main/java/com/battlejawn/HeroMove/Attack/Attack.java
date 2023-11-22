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
    private String newMessage;
    private final Logger logger = Logger.getLogger(Attack.class.getName());

    public void useAttack(Hero hero, Enemy enemy, BattleSession battleSession, String move) {
        logger.info("Inside useAttack method. Hero: " + hero + ". Enemy: " + enemy + ". Battle Session: " + battleSession + ". Move: " + move + ".");

        switch (move) {
            case "Wand":    Wand wand = new Wand();
                            damage = wand.attack();
                            //Change to EnemyRepository method that updates enemy health in database
                            enemy.takeDamage(damage);
                            newMessage = newMessageGenerator("Wand", damage);
                            battleSession.addNewMessage(newMessage);
                            break;
            case "Strike":  Strike strike = new Strike();
                            damage = strike.attack();
                            enemy.takeDamage(damage);
                            newMessage = newMessageGenerator("Strike", damage);
                            battleSession.addNewMessage(newMessage);
                            break;
            case "Stab":    Stab stab = new Stab();
                            damage = stab.attack();
                            enemy.takeDamage(damage);
                            logger.info("Inside useAttack method. Enemy health: " + enemy.getHealth() + ". Damage: " + damage);
                            newMessage = newMessageGenerator("Stab", damage);
                            battleSession.addNewMessage(newMessage);
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
