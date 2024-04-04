package com.battlejawn.Purge;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Repository.BattleHistoryMessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BattleHistoryMessagePurge {
    private final BattleHistoryMessageRepository battleHistoryMessageRepository;
    private final Logger logger = Logger.getLogger(BattleHistoryMessagePurge.class.getName());

    public BattleHistoryMessagePurge(BattleHistoryMessageRepository battleHistoryMessageRepository) {
        this.battleHistoryMessageRepository = battleHistoryMessageRepository;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void purgeBattleHistoryMessage() {
        logger.info("Inside purgeBattleHistoryMessage service class. This job runs every hour.");
        List<BattleHistoryMessage> battleHistoryMessageList = battleHistoryMessageRepository.findAll();
        Duration timeDifference;
        Duration oneDay = Duration.ofDays(5);
        for (BattleHistoryMessage battleHistoryMessage: battleHistoryMessageList) {
            timeDifference = Duration.between(battleHistoryMessage.getCreatedAt(), LocalDateTime.now());
            try {
                if (timeDifference.compareTo(oneDay) > 0) {
                    logger.info("Inside BattleHistoryMessagePurge service class. Purging battleHistoryMessage with ID: " + battleHistoryMessage.getId() + ".");
                    battleHistoryMessageRepository.deleteById(battleHistoryMessage.getId());
                }
            }catch(Exception e) {
                throw new RuntimeException("BattleHistoryMessagePurge failed to delete battleHistoryMessage: " + battleHistoryMessage.getId() + ". Error: " + e.getMessage() + ".");
            }
        }
    }
}
