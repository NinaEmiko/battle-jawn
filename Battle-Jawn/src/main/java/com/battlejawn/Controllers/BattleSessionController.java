package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Service.BattleSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/battle-session")
public class BattleSessionController {

    private final BattleSessionService battleSessionService;
    private JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(BattleSessionController.class.getName());


    public BattleSessionController(BattleSessionService battleSessionService) {
        this.battleSessionService = battleSessionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BattleSession> getBattleSessionById(@PathVariable("id") Long id) {
        logger.info("Inside Battle Controller ID: " + id);
        BattleSession battleSession = battleSessionService.getBattleSessionById(id);

        if (battleSession != null) {
            return new ResponseEntity<>(battleSession, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BattleSession> createNewBattleSession(@RequestBody String heroId) {
        logger.info("Inside createNewBattleSession Controller method" + heroId);
        jsonParser = new JsonParser();
        Long parsedHeroId = jsonParser.extractHeroId(heroId);
        logger.info("Parsed Hero ID: " + parsedHeroId);
        BattleSession battleSession = battleSessionService.createNewBattleSession(parsedHeroId);
        if (battleSession != null) {
            URI location = URI.create("/battleSession/" + battleSession.getId());
            logger.info("Location: " + location);
            logger.info("Battle Session data: " + battleSession);
            return ResponseEntity.created(location).body(battleSession);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
