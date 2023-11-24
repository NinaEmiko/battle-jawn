package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Repository.BattleHistoryMessageRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BattleHistoryMessageService {

    private final BattleHistoryMessageRepository battleHistoryMessageRepository;

    public BattleHistoryMessageService(BattleHistoryMessageRepository battleHistoryMessageRepository) {
        this.battleHistoryMessageRepository = battleHistoryMessageRepository;
    }


    public BattleHistoryMessage createNewMessage(Long battleSessionId, String message) {
        BattleHistoryMessage battleHistoryMessage = new BattleHistoryMessage();
        battleHistoryMessage.setMessage(message);
        battleHistoryMessage.setCreatedAt(LocalDateTime.now());
        battleHistoryMessage.setBattleSessionId(battleSessionId);
        battleHistoryMessageRepository.save(battleHistoryMessage);
        return battleHistoryMessage;

    }

    public ArrayList<String> getBattleHistoryMessagesById(Long id) {
        return null;
    }
}
