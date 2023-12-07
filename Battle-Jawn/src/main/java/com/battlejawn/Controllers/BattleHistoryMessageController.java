package com.battlejawn.Controllers;

import com.battlejawn.Service.BattleHistoryMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/battle-history-message")
public class BattleHistoryMessageController {
    @Autowired
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final Logger logger = Logger.getLogger(BattleHistoryMessageController.class.getName());

    public BattleHistoryMessageController(BattleHistoryMessageService battleHistoryMessageService){
        this.battleHistoryMessageService = battleHistoryMessageService;
    }

    @GetMapping("/{id}")
    public List<String> getBattleHistoryMessagesByBattleSessionId(@PathVariable Long id) {
        logger.info("Inside getAllBattleHistoryMessagesById service method. ID: " + id);
        return battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(id);
    }
}
