package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.HeroMove.Attack.Attack;
import com.battlejawn.HeroMove.Heal.Heal;
import com.battlejawn.HeroMove.StrongAttack.StrongAttack;
import org.springframework.stereotype.Service;

@Service
public class HeroMoveService {
    private final BattleSessionService battleSessionService;
    private final HeroService heroService;
    private final EnemyService enemyService;
    private Attack attack;
    private StrongAttack strongAttack;
    private Heal heal;

    public HeroMoveService(BattleSessionService battleSessionService, HeroService heroService, EnemyService enemyService) {
        this.battleSessionService = battleSessionService;
        this.heroService = heroService;
        this.enemyService = enemyService;
    }

    public void heroMove(String move, Long battleSessionId) {
        attack = new Attack();
        strongAttack = new StrongAttack();
        heal = new Heal();

        BattleSession battleSession = battleSessionService.getBattleSessionById(battleSessionId);
        Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());
        Hero hero = heroService.getHeroById(battleSession.getHeroId());

        switch (move) {
            case "Attack":  attack.useAttack(hero, enemy, battleSession);
                break;
            case "StrongAttack":  strongAttack.useAttack(hero, enemy, battleSession);
                break;
        }
    }
}
