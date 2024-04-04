package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Repository.BattleHistoryMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BattleHistoryMessageService {

    private final BattleHistoryMessageRepository battleHistoryMessageRepository;

    @Transactional
    public BattleHistoryMessage createNewMessage(Long battleSessionId, String message) {
        BattleHistoryMessage battleHistoryMessage = new BattleHistoryMessage();
        battleHistoryMessage.setMessage(message);
        battleHistoryMessage.setCreatedAt(LocalDateTime.now());
        battleHistoryMessage.setBattleSessionId(battleSessionId);
        battleHistoryMessageRepository.save(battleHistoryMessage);
        return battleHistoryMessage;

    }

    public List<String> getBattleHistoryMessagesByBattleSessionId(Long id) {
        return battleHistoryMessageRepository.findMessagesByBattleSessionId(id);
    }
}
