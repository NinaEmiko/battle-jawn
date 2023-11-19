package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Config.UserResponse;
import com.battlejawn.Entities.Battle.Battle;
import com.battlejawn.Entities.Battle.BattleHistory;
import com.battlejawn.Service.BattleHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/battle-history")
public class BattleHistoryController {

    private final BattleHistoryService battleHistoryService;
    private UserResponse userResponse;
    private JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(BattleHistoryController.class.getName());


    public BattleHistoryController(BattleHistoryService battleHistoryService) {
        this.battleHistoryService = battleHistoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BattleHistory> getBattleHistoryById(@PathVariable("id") Long id) {
        logger.info("Inside Battle Controller ID: " + id);
        BattleHistory battleHistory = battleHistoryService.getBattleHistoryById(id);

        if (battleHistory != null) {
            return new ResponseEntity<>(battleHistory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BattleHistory> createNewBattleHistory() {
        logger.info("Inside createNewBattleHistory Controller method");
        BattleHistory battleHistory = battleHistoryService.createNewBattleHistory();
        if (battleHistory != null) {
            URI location = URI.create("/battleHistory/" + battleHistory.getId());
            logger.info("Location: " + location);
            logger.info("createNewBattleHistory api POST call Response: " + userResponse);
            return ResponseEntity.created(location).body(battleHistory);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
