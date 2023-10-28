package com.battlejawn.PlayerMove.StrongAttack;

import com.battlejawn.Entities.Battle;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.BattleRepository;
import com.battlejawn.Repository.EnemyRepository;
import com.battlejawn.Repository.HeroRepository;

public class StrongAttack {
    
    private String role;
    private int damage;
    private Battle battle;
    private BattleRepository battleRepository;
    private Enemy enemy;
    private int enemyCurrentHealth;
    private String newMessage;
    private EnemyRepository enemyRepository;
    private HeroRepository heroRepository;

    public void useAttack(Long playerId, Long enemyId, Long battleId) {
        role = heroRepository.findById(playerId).get().getRole();
        enemyCurrentHealth = enemyRepository.findById(enemyId).get().getHealth();
        battle = battleRepository.findById(battleId).get();

        switch (role) {
            case "Caster":  Blast blast = new Blast();
                            damage = blast.attack();
                            newMessage = newMessageGenerator("Blast", damage);
                            battle.getMessages().add(newMessage);
                            break;
            case "Healer":  Holy holy = new Holy();
                            damage = holy.attack();
                            newMessage = newMessageGenerator("Holy", damage);
                            battle.getMessages().add(newMessage);
                            break;
            case "Tank":    Impale impale = new Impale();
                            damage = impale.attack();
                            newMessage = newMessageGenerator("Impale", damage);
                            battle.getMessages().add(newMessage);
                            break;
            case "DPS":     BackStab backStab = new BackStab();
                            damage = backStab.attack();
                            newMessage = newMessageGenerator("Backstab", damage);
                            battle.getMessages().add(newMessage);
                            break;
        }

        enemy.setHealth(enemyCurrentHealth - damage);

        battleRepository.save(battle);
        enemyRepository.save(enemy);
    }

    public String newMessageGenerator(String name, int damage) {
        if (damage > 0) {
            return name + " did " + damage + " damage!"; 
        } else { 
            return name + " missed!";
            }
        }
}
