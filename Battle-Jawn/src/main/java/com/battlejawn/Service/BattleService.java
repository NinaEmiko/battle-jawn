package com.battlejawn.Service;

import com.battlejawn.Entities.Battle;
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

    @Transactional
    public Battle startNewBattle() {
        logger.info("Inside startNewBattle Service method");
        try {
            Battle battle = new Battle();
            String openingMessage = "(BattleService Class) You encountered an enemy!";
            battle.addToMessages(openingMessage);
            battleRepository.save(battle);
            return battle;
        } catch(Exception e) {
            throw new RuntimeException("Failed to start new battle: " + e.getMessage());
        }
    }

    public Battle getBattleById(Long id) {
        logger.info("Inside Battle Service ID: " + id);
        Optional<Battle> battle = battleRepository.findById(id);
        if (battle.isPresent()) {
            logger.info("Inside Battle isPresent");
            return battle.get();
        } else {
            throw new EntityNotFoundException("Battle with ID " + id + " not found");
        }
    }

}