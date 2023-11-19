 package com.battlejawn.Controllers;

 import com.battlejawn.Config.JsonParser;
 import com.battlejawn.Config.UserResponse;
 import com.battlejawn.Entities.Battle.BattleSession;
 import com.battlejawn.Service.BattleSessionService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;
 import java.net.URI;
 import java.util.logging.Logger;

 @RestController
 @RequestMapping("/api/battle-session")
 public class BattleSessionController {

     private final BattleSessionService battleSessionService;
     private UserResponse userResponse;
     private JsonParser jsonParser;
     private final Logger logger = Logger.getLogger(BattleSessionController.class.getName());

     @Autowired
     public BattleSessionController(BattleSessionService battleSessionService) {
         this.battleSessionService = battleSessionService;
     }

     @PutMapping
     public ResponseEntity<UserResponse> useAttack(@RequestBody String move){
         jsonParser = new JsonParser();

         String btn = jsonParser.extractButton(move);
         Long heroId = jsonParser.extractHeroId(move);
         Long enemyId = jsonParser.extractEnemyId(move);
         Long battleHistoryId = jsonParser.extractBattleHistoryId(move);

         logger.info("Inside useAttack");
         BattleSession battle = battleSessionService.useAttack(btn, heroId, enemyId, battleHistoryId);
         try {
             URI location = URI.create("/battleHistory/" + battle.getId());
             logger.info("Location: " + location);
             userResponse = new UserResponse(location, battle.getId());
             logger.info("useAttack api PUT call Response: " + userResponse);
             return ResponseEntity.created(location).body(userResponse);
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
     }

     @PostMapping("/start")
     public ResponseEntity<BattleSession> createNewBattleSession(@RequestBody String request) {
         jsonParser = new JsonParser();
         Long heroId = jsonParser.extractHeroId(request);
         logger.info("Inside createNewBattleSession Controller method");
         BattleSession battleSession = battleSessionService.createNewBattleSession(heroId);
         if (battleSession != null) {
             URI location = URI.create("/battleSession/" + battleSession.getId());
             logger.info("Location: " + location);
             logger.info("Battle History data: " + battleSession);
             return ResponseEntity.created(location).body(battleSession);
         } else {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
     }
 }
