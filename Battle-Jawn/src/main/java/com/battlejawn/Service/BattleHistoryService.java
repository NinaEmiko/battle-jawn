package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.Battle;
import com.battlejawn.Entities.Battle.BattleHistory;
import com.battlejawn.Repository.BattleHistoryRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Logger;

public class BattleHistoryService {
    private final BattleHistoryRepository battleHistoryRepository;
    private final Logger logger = Logger.getLogger(BattleService.class.getName());

    public BattleHistoryService(BattleHistoryRepository battleHistoryRepository) {
        this.battleHistoryRepository = battleHistoryRepository;
    }

    public BattleHistory getBattleHistoryById(Long id) {
        logger.info("Inside Battle History Service ID: " + id);
        Optional<BattleHistory> battleHistory = battleHistoryRepository.findById(id);
        if (battleHistory.isPresent()) {
            logger.info("Inside BattleHistory isPresent");
            return battleHistory.get();
        } else {
            throw new EntityNotFoundException("BattleHistory with ID " + id + " not found");
        }
    }
    @Transactional
    public BattleHistory createNewBattleHistory() {
        logger.info("Inside createNewBattleHistory Service method");
        try {
            BattleHistory battleHistory = new BattleHistory();
            String openingMessage = "(BattleHistoryService Class) You encountered an enemy!";
            battleHistory.addNewMessage(openingMessage);
            battleHistoryRepository.save(battleHistory);
            return battleHistory;
        } catch(Exception e) {
            throw new RuntimeException("Failed to create new battle history: " + e.getMessage());
        }
    }
}
