package com.battlejawn.HeroMove.Attack;

import com.battlejawn.Entities.Battle.Battle;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.BattleRepository;
import com.battlejawn.Repository.EnemyRepository;
import com.battlejawn.Repository.HeroRepository;

public class Attack {

    private int damage;
    private BattleRepository battleRepository;
    private Enemy enemy;
    private EnemyRepository enemyRepository;
    private HeroRepository heroRepository;

    public void useAttack(Long playerId, Long enemyId, Long battleId) {
        String role = heroRepository.findById(playerId).get().getRole();
        int enemyCurrentHealth = enemyRepository.findById(enemyId).get().getHealth();
        Battle battle = battleRepository.findById(battleId).get();
        String newMessage;

        switch (role) {
            case "Caster", "Healer":  Wand wand = new Wand();
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
