package com.battlejawn.PlayerMove.Attack;

import com.battlejawn.Entities.Battle;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.BattleRepository;
import com.battlejawn.Repository.EnemyRepository;
import com.battlejawn.Repository.HeroRepository;

public class Attack {
    
    private String role;
    private int damage;
    private Battle battle;
    private BattleRepository battleRepository;
    private Enemy enemy;
    private int enemyCurrentHealth;
    private String newMessage;
    private EnemyRepository enemyRepository;
    private HeroRepository heroRepository;
    private Wand wand;

    public void useAttack(Long playerId, Long enemyId, Long battleId) {
        role = heroRepository.findById(playerId).get().getRole();
        enemyCurrentHealth = enemyRepository.findById(enemyId).get().getHealth();
        battle = battleRepository.findById(battleId).get();
        wand = new Wand();

        switch (role) {
            case "Caster":  damage = wand.attack();
                            newMessage = newMessageGenerator("Wand", damage);
                            battle.getMessages().add(newMessage);
                            break;
            case "Healer":  
                            damage = wand.attack();
                            newMessage = newMessageGenerator("Wand", damage);
                            battle.getMessages().add(newMessage);
                            break;
            case "Tank":    Strike strike = new Strike();
                            damage = strike.attack();
                            newMessage = newMessageGenerator("Strike", damage);
                            battle.getMessages().add(newMessage);
                            break;
            case "DPS":     Stab stab = new Stab();
                            damage = stab.attack();
                            newMessage = newMessageGenerator("Stab", damage);
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
