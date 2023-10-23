package com.battlejawn.PlayerMove.StrongAttack;

import com.battlejawn.Entities.BattleHistory;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.BattleHistoryRepository;
import com.battlejawn.Repository.EnemyRepository;
import com.battlejawn.Repository.ToonRepository;

public class StrongAttack {
    
    private String role;
    private int damage;
    private BattleHistory battleHistory;
    private BattleHistoryRepository battleHistoryRepository;
    private Enemy enemy;
    private int enemyCurrentHealth;
    private String newMessage;
    private EnemyRepository enemyRepository;
    private ToonRepository toonRepository;

    public void useAttack(Long playerId, Long enemyId, Long battleId) {
        role = toonRepository.findById(playerId).get().getRole();
        enemyCurrentHealth = enemyRepository.findById(enemyId).get().getHealth();
        battleHistory = battleHistoryRepository.findById(battleId).get();

        switch (role) {
            case "Caster":  Blast blast = new Blast();
                            damage = blast.attack();
                            newMessage = newMessageGenerator("Blast", damage);
                            battleHistory.getMessages().add(newMessage);
                            break;
            case "Healer":  Holy holy = new Holy();
                            damage = holy.attack();
                            newMessage = newMessageGenerator("Holy", damage);
                            battleHistory.getMessages().add(newMessage);
                            break;
            case "Tank":    Impale impale = new Impale();
                            damage = impale.attack();
                            newMessage = newMessageGenerator("Impale", damage);
                            battleHistory.getMessages().add(newMessage);
                            break;
            case "DPS":     BackStab backStab = new BackStab();
                            damage = backStab.attack();
                            newMessage = newMessageGenerator("Backstab", damage);
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
