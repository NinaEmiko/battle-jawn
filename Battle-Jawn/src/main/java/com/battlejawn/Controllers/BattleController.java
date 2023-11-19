 package com.battlejawn.Controllers;

 import com.battlejawn.Config.JsonParser;
 import com.battlejawn.Config.UserResponse;
 import com.battlejawn.Entities.Battle.Battle;
 import com.battlejawn.Entities.Battle.BattleHistory;
 import com.battlejawn.Service.BattleService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;
 import java.net.URI;
 import java.util.logging.Logger;

 @RestController
 @RequestMapping("/api/battle")
 public class BattleController {

     private final BattleService battleService;
     private UserResponse userResponse;
     private JsonParser jsonParser;
     private final Logger logger = Logger.getLogger(BattleController.class.getName());

     @Autowired
     public BattleController(BattleService battleService) {
         this.battleService = battleService;
     }

     @PutMapping
     public ResponseEntity<UserResponse> useAttack(@RequestBody String move){
         jsonParser = new JsonParser();

         String btn = jsonParser.extractButton(move);
         Long heroId = jsonParser.extractHeroId(move);
         Long enemyId = jsonParser.extractEnemyId(move);
         Long battleHistoryId = jsonParser.extractBattleHistoryId(move);

         logger.info("Inside useAttack");
         Battle battle = battleService.useAttack(btn, heroId, enemyId, battleHistoryId);
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

 }
