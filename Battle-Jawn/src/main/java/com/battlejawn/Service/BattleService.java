package com.battlejawn.Service;

import com.battlejawn.Entities.Battle;
import com.battlejawn.HeroMove.Run;
import com.battlejawn.HeroMove.Attack.Attack;
import com.battlejawn.HeroMove.Heal.Heal;
import com.battlejawn.HeroMove.StrongAttack.StrongAttack;
import com.battlejawn.Repository.BattleRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class BattleService {
    private BattleRepository battleRepository;
    private Attack attack;
    private StrongAttack strongAttack;
    private Heal heal;
    private Run run;

    public Battle useAttack(int button, Long heroId, Long enemyId, Long battleId) {
        switch (button) {
            case 1: attack.useAttack(heroId, enemyId, battleId);
            case 2: heal.useHeal();
            case 3: strongAttack.useAttack(heroId, enemyId, battleId);
            case 4: run.useRun(heroId);;
        }
        return battleRepository.getById(battleId);
    }

    @Transactional
    public Battle startNewBattle(Long json) {
        try {
            Battle battle = new Battle();
            battle.setHeroId(json);
            battle.setEnemyId(json);
            String openingMessage = "You encountered an enemy!";
            battle.getMessages().add(openingMessage);
            battleRepository.save(battle);
            return battle;
        } catch(Exception e) {
            throw new RuntimeException("Failed to start new battle: " + e.getMessage());
        }
    }

}