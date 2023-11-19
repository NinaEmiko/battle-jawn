package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistory;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.HeroMove.Run;
import com.battlejawn.HeroMove.Attack.Attack;
import com.battlejawn.HeroMove.Heal.Heal;
import com.battlejawn.HeroMove.StrongAttack.StrongAttack;
import com.battlejawn.Repository.BattleSessionRepository;
import com.battlejawn.Repository.EnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BattleSessionService {
    private final BattleSessionRepository battleSessionRepository;
    private final EnemyService enemyService;
    private final BattleHistoryService battleHistoryService;
    private final Logger logger = Logger.getLogger(BattleSessionService.class.getName());
    private Attack attack;
    private StrongAttack strongAttack;
    private Heal heal;
    private Run run;
    @Autowired
    public BattleSessionService(BattleSessionRepository battleSessionRepository, EnemyService enemyService, BattleHistoryService battleHistoryService) {
        this.battleSessionRepository = battleSessionRepository;
        this.enemyService = enemyService;
        this.battleHistoryService = battleHistoryService;
    }

    public BattleSession useAttack(String button, Long heroId, Long enemyId, Long battleId) {
        switch (button) {
            case "Wand": attack.useAttack(heroId, enemyId, battleId);
            case "Heal": heal.useHeal();
            case "Blast": strongAttack.useAttack(heroId, enemyId, battleId);
            case "Run": run.useRun(heroId);;
        }
        return battleSessionRepository.getById(battleId);
    }

    public BattleSession createNewBattleSession(Long heroId) {
        logger.info("Inside createNewBattleSession Service method");
        try {
            BattleSession battleSession = new BattleSession();
            battleSession.setHeroId(heroId);
            Enemy enemy = enemyService.createNewEnemy();
            battleSession.setEnemyId(enemy.getId());
            BattleHistory battleHistory = battleHistoryService.createNewBattleHistory();
            battleSession.setBattleHistory(battleHistory);
            logger.info("Battle Session Hero Id: " + battleSession.getHeroId());
            battleSessionRepository.save(battleSession);
            logger.info("Newly Created Battle Session: " + battleSession);
            return battleSession;
        } catch(Exception e) {
            throw new RuntimeException("Failed to create new battle session: " + e.getMessage());
        }
    }
}