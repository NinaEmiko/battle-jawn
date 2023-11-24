package com.battlejawn.Service;

import com.battlejawn.EnemyMove.Strike;
import com.battlejawn.Entities.Hero.Hero;
import org.springframework.stereotype.Service;

@Service
public class EnemyMoveService {
    private final HeroService heroService;
    private final BattleHistoryMessageService battleHistoryMessageService;

    public EnemyMoveService(HeroService heroService, BattleHistoryMessageService battleHistoryMessageService) {
        this.heroService = heroService;
        this.battleHistoryMessageService = battleHistoryMessageService;
    }

    public void useEnemyMove(Long heroId, Long battleSessionId){
        Strike strike = new Strike();
        int damage = strike.attack();
        Hero hero = heroService.getHeroById(heroId);
        int updatedHeroHealth;
        if (damage > hero.getHealth()) {
            updatedHeroHealth = 0;
        } else {
            updatedHeroHealth = hero.getHealth() - damage;
        }
        heroService.updateHealthById(updatedHeroHealth, hero.getId());
        String newMessage;
        if (damage > 0) {
            newMessage = "Enemy strike" + " did " + damage + " damage.";
        } else {
            newMessage = "Enemy strike missed!";
        }
        battleHistoryMessageService.createNewMessage(battleSessionId, newMessage);
    }
}
