package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Service.BattleSessionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/battle-session")
@AllArgsConstructor
public class BattleSessionController {

    @Autowired
    private final BattleSessionService battleSessionService;
    private final JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(BattleSessionController.class.getName());

    @GetMapping("/{id}")
    public ResponseEntity<BattleSession> getBattleSessionById(@PathVariable("id") Long id) {
        logger.info("Inside getBattleSessionById controller method. Battle Session ID: " + id + ".");
        BattleSession battleSession = battleSessionService.getBattleSessionById(id);

        if (battleSession != null) {
            return new ResponseEntity<>(battleSession, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BattleSession> createNewBattleSession(@RequestBody String heroId) {
        logger.info("Inside createNewBattleSession controller method. Hero ID: " + heroId + ".");
        Long parsedHeroId = jsonParser.extractHeroId(heroId);
        BattleSession battleSession = battleSessionService.createNewBattleSession(parsedHeroId);
        if (battleSession != null) {
            URI location = URI.create("/battleSession/" + battleSession.getId());
            return ResponseEntity.created(location).body(battleSession);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/end", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> processEndOfBattle(@RequestBody String data) {
        logger.info("Inside processEndOfBattle controller method. Data: " + data + ".");
        Long battleSessionId = jsonParser.extractBattleSessionId(data);
        String battleResult = jsonParser.extractBattleResult(data);
        String endOfBattleMessage = battleSessionService.processEndOfBattle(battleSessionId, battleResult);

        if (endOfBattleMessage != null) {
            return new ResponseEntity<>(endOfBattleMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
