package com.battlejawn.HeroMove.Attack;

import com.battlejawn.Entities.Battle.BattleHistory;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.BattleHistoryRepository;
import com.battlejawn.Repository.EnemyRepository;
import com.battlejawn.Repository.HeroRepository;

public class Attack {

    private int damage;
    private BattleHistoryRepository battleHistoryRepository;
    private Enemy enemy;
    private EnemyRepository enemyRepository;
    private HeroRepository heroRepository;

    public void useAttack(Long playerId, Long enemyId, Long battleId) {
        String role = heroRepository.findById(playerId).get().getRole();
        int enemyCurrentHealth = enemyRepository.findById(enemyId).get().getHealth();
        BattleHistory battleHistory = battleHistoryRepository.findById(battleId).get();
        String newMessage;

        switch (role) {
            case "Caster", "Healer":  Wand wand = new Wand();
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
