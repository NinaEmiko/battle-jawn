package com.battlejawn.Purge;

import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Repository.BattleSessionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BattleSessionPurge {
    private final BattleSessionRepository battleSessionRepository;
    private final Logger logger = Logger.getLogger(BattleSessionPurge.class.getName());

    public BattleSessionPurge(BattleSessionRepository battleSessionRepository) {
        this.battleSessionRepository = battleSessionRepository;
    }

    @Scheduled(cron = "0 10 * * * *")
    public void purgeBattleSession() {
        logger.info("Inside purgeBattleSession service class. This job runs every hour.");
        List<BattleSession> battleSessionList = battleSessionRepository.findAll();
        Duration timeDifference;
        Duration oneDay = Duration.ofDays(5);
        for (BattleSession battleSession: battleSessionList) {
            timeDifference = Duration.between(battleSession.getCreatedAt(), LocalDateTime.now());
            try {
                if (timeDifference.compareTo(oneDay) > 0) {
                    logger.info("Inside BattleSessionPurge service class. Purging battleSession with ID: " + battleSession.getId() + ".");
                    battleSessionRepository.deleteById(battleSession.getId());
                }
            }catch(Exception e) {
                throw new RuntimeException("BattleSessionPurge failed to delete battleSession: " + battleSession.getId() + ". Error: " + e.getMessage() + ".");
            }
        }
    }
}
