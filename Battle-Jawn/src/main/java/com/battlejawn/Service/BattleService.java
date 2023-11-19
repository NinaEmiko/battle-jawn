package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.Battle;
import com.battlejawn.HeroMove.Run;
import com.battlejawn.HeroMove.Attack.Attack;
import com.battlejawn.HeroMove.Heal.Heal;
import com.battlejawn.HeroMove.StrongAttack.StrongAttack;
import com.battlejawn.Repository.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BattleService {
    private final BattleRepository battleRepository;
    private final Logger logger = Logger.getLogger(BattleService.class.getName());
    private Attack attack;
    private StrongAttack strongAttack;
    private Heal heal;
    private Run run;
    @Autowired
    public BattleService(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    public Battle useAttack(String button, Long heroId, Long enemyId, Long battleId) {
        switch (button) {
            case "Wand": attack.useAttack(heroId, enemyId, battleId);
            case "Heal": heal.useHeal();
            case "Blast": strongAttack.useAttack(heroId, enemyId, battleId);
            case "Run": run.useRun(heroId);;
        }
        return battleRepository.getById(battleId);
    }
}