package com.battlejawn.PlayerMove.Attack;

import com.battlejawn.Entities.BattleHistory;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.BattleHistoryRepository;
import com.battlejawn.Repository.EnemyRepository;
import com.battlejawn.Repository.ToonRepository;

public class Attack {
    
    private String role;
    private int damage;
    private BattleHistory battleHistory;
    private BattleHistoryRepository battleHistoryRepository;
    private Enemy enemy;
    private int enemyCurrentHealth;
    private String newMessage;
    private EnemyRepository enemyRepository;
    private ToonRepository toonRepository;
    private Wand wand;

    public void useAttack(Long playerId, Long enemyId, Long battleId) {
        role = toonRepository.findById(playerId).get().getRole();
        enemyCurrentHealth = enemyRepository.findById(enemyId).get().getHealth();
        battleHistory = battleHistoryRepository.findById(battleId).get();
        wand = new Wand();

        switch (role) {
            case "Caster":  damage = wand.attack();
                            newMessage = newMessageGenerator("Wand", damage);
                            battleHistory.getMessages().add(newMessage);
                            break;
            case "Healer":  
                            damage = wand.attack();
                            newMessage = newMessageGenerator("Wand", damage);
                            battleHistory.getMessages().add(newMessage);
                            break;
            case "Tank":    Strike strike = new Strike();
                            damage = strike.attack();
                            newMessage = newMessageGenerator("Strike", damage);
                            battleHistory.getMessages().add(newMessage);
                            break;
            case "DPS":     Stab stab = new Stab();
                            damage = stab.attack();
                            newMessage = newMessageGenerator("Stab", damage);
                            battleHistory.getMessages().add(newMessage);
                            break;
        }

        enemy.setHealth(enemyCurrentHealth - damage);

        battleHistoryRepository.save(battleHistory);
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
