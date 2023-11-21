package com.battlejawn.HeroMove.StrongAttack;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Repository.BattleSessionRepository;
import com.battlejawn.Repository.EnemyRepository;
import com.battlejawn.Repository.HeroRepository;

public class StrongAttack {
    private int damage;
    private String role;
    private String newMessage;

    public void useAttack(Hero hero, Enemy enemy, BattleSession battleSession) {
        role = hero.getRole();
        int enemyCurrentHealth = enemy.getHealth();

        switch (role) {
            case "Caster":  Blast blast = new Blast();
                            damage = blast.attack();
                            newMessage = newMessageGenerator("Blast", damage);
                            battleSession.getBattleHistory().add(newMessage);
                            break;
            case "Healer":  Holy holy = new Holy();
                            damage = holy.attack();
                            newMessage = newMessageGenerator("Holy", damage);
                            battleSession.getBattleHistory().add(newMessage);
                            break;
            case "Tank":    Impale impale = new Impale();
                            damage = impale.attack();
                            newMessage = newMessageGenerator("Impale", damage);
                            battleSession.getBattleHistory().add(newMessage);
                            break;
            case "DPS":     BackStab backStab = new BackStab();
                            damage = backStab.attack();
                            newMessage = newMessageGenerator("Backstab", damage);
                            battleSession.getBattleHistory().add(newMessage);
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
