package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.BattleSessionRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public class BattleSessionService {
    private final BattleSessionRepository battleSessionRepository;
    private final EnemyService enemyService;
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final Logger logger = Logger.getLogger(BattleSessionService.class.getName());

    public BattleSessionService(BattleSessionRepository battleSessionRepository, EnemyService enemyService, BattleHistoryMessageService battleHistoryMessageService) {
        this.battleSessionRepository = battleSessionRepository;
        this.enemyService = enemyService;
        this.battleHistoryMessageService = battleHistoryMessageService;
    }

    public BattleSession getBattleSessionById(Long id) {
        logger.info("Inside getBattleSessionById service method. Battle Session ID: " + id + ".");
        Optional<BattleSession> battleSession = battleSessionRepository.findById(id);
        if (battleSession.isPresent()) {
            return battleSession.get();
        } else {
            throw new EntityNotFoundException("BattleSession with ID " + id + " not found");
        }
    }

//    @Transactional
//    public void addMessageToBattleHistory(String message, Long battleSessionId) {
//        logger.info("Inside addMessageToBattleHistory service method. New Message: " + message + ".");
//        Optional<BattleSession> optionalBattleSession = battleSessionRepository.findById(battleSessionId);
//        if (optionalBattleSession.isPresent()) {
//            BattleSession battleSession = optionalBattleSession.get();
//
//            battleHistoryMessageService.createNewMessage(battleSession.getBattleHistoryMessageId(), message);
//
//            ArrayList<String> updatedList = battleHistoryMessageService.getBattleHistoryMessagesById(battleSession.getBattleHistoryMessageId());
//        }
//    }

    @Transactional
    public BattleSession createNewBattleSession(Long heroId) {
        logger.info("Inside createNewBattleSession service method. Hero ID: " + heroId + ".");
        try {
            Enemy enemy = enemyService.createNewEnemy();

            BattleSession battleSession = new BattleSession();

            battleSession.setEnemyId(enemy.getId());
            battleSession.setHeroId(heroId);

            battleSessionRepository.save(battleSession);

            BattleHistoryMessage battleHistoryMessage = battleHistoryMessageService.createNewMessage(battleSession.getId(), "You encountered an enemy!");
            Long battleHistoryMessageId = battleHistoryMessage.getId();
            battleSession.setBattleHistoryMessageId(battleHistoryMessageId);

            return battleSession;
        } catch(Exception e) {
            throw new RuntimeException("Failed to create new battle session: " + e.getMessage());
        }
    }
}
