package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Repository.BattleSessionRepository;
import com.battlejawn.Repository.EnemyRepository;
import com.battlejawn.Repository.HeroRepository;

public class StrongAttack {
    private int damage;
    private String newMessage;

    public void useAttack(Hero hero, Enemy enemy, BattleSession battleSession, String move) {

        switch (move) {
            case "Blast":       Blast blast = new Blast();
                                damage = blast.attack();
                                enemy.takeDamage(damage);
                                newMessage = newMessageGenerator("Blast", damage);
                                battleSession.getBattleHistory().add(newMessage);
                                break;
            case "Holy":        Holy holy = new Holy();
                                damage = holy.attack();
                                enemy.takeDamage(damage);
                                newMessage = newMessageGenerator("Holy", damage);
                                battleSession.getBattleHistory().add(newMessage);
                                break;
            case "Impale":      Impale impale = new Impale();
                                damage = impale.attack();
                                enemy.takeDamage(damage);
                                newMessage = newMessageGenerator("Impale", damage);
                                battleSession.getBattleHistory().add(newMessage);
                                break;
            case "BackStab":    BackStab backStab = new BackStab();
                                damage = backStab.attack();
                                enemy.takeDamage(damage);
                                newMessage = newMessageGenerator("Backstab", damage);
                                battleSession.getBattleHistory().add(newMessage);
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
