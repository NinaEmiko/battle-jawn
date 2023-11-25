package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Repository.BattleHistoryMessageRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BattleHistoryMessageService {

    private final BattleHistoryMessageRepository battleHistoryMessageRepository;

    public BattleHistoryMessageService(BattleHistoryMessageRepository battleHistoryMessageRepository) {
        this.battleHistoryMessageRepository = battleHistoryMessageRepository;
    }

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
