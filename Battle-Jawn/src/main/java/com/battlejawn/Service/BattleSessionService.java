package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.BattleSessionRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public class BattleSessionService {
    private final BattleSessionRepository battleSessionRepository;
    private final EnemyService enemyService;
    private final Logger logger = Logger.getLogger(BattleSessionService.class.getName());

    public BattleSessionService(BattleSessionRepository battleSessionRepository, EnemyService enemyService) {
        this.battleSessionRepository = battleSessionRepository;
        this.enemyService = enemyService;
    }

    public BattleSession getBattleSessionById(Long id) {
        logger.info("Inside Battle Session Service ID: " + id);
        Optional<BattleSession> battleSession = battleSessionRepository.findById(id);
        if (battleSession.isPresent()) {
            logger.info("Inside BattleSession isPresent");
            return battleSession.get();
        } else {
            throw new EntityNotFoundException("BattleSession with ID " + id + " not found");
        }
    }

    @Transactional
    public void addMessageToBattleHistory(ArrayList<String> message, Long battleSessionId) {
        logger.info("Inside addMessageToBattleHistory Service. New Message: " + message);
        battleSessionRepository.addMessageToBattleHistory(message, battleSessionId);
    }

    @Transactional
    public BattleSession createNewBattleSession(Long heroId) {
        logger.info("Inside createNewBattleSession Service method");
        try {
            BattleSession battleSession = new BattleSession();
            Enemy enemy = enemyService.createNewEnemy();
            battleSession.setEnemyId(enemy.getId());
            battleSession.setHeroId(heroId);
            String openingMessage = "You encountered an enemy!";
            battleSession.addNewMessage(openingMessage);
            battleSessionRepository.save(battleSession);
            return battleSession;
        } catch(Exception e) {
            throw new RuntimeException("Failed to create new battle session: " + e.getMessage());
        }
    }
}
