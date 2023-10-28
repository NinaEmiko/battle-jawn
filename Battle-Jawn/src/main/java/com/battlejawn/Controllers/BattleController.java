 package com.battlejawn.Controllers;

 import com.battlejawn.Config.JsonParser;
 import com.battlejawn.Config.UserResponse;
 import com.battlejawn.Entities.Battle;
 import com.battlejawn.Entities.Enemy.Enemy;
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

     private BattleService battleService;
     private UserResponse userResponse;
     private JsonParser jsonParser;
     private final Logger logger = Logger.getLogger(BattleController.class.getName());

     @Autowired
     public BattleController(BattleService battleService) {
         this.battleService = battleService;
     }

     @PostMapping
     //Receive json object with heroId and enemyId
     public ResponseEntity<UserResponse> startNewBattle(@RequestBody Long json) {
         logger.info("Inside startNewBattle");
         Battle battle = battleService.startNewBattle(json);
         if (battle != null) {
             URI location = URI.create("/battle/" + battle.getId());
             logger.info("Location: " + location);
             userResponse = new UserResponse(location, battle.getId());
             logger.info("addHero api POST call Response: " + userResponse);
             return ResponseEntity.created(location).body(userResponse);
         } else {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
     }

     @PutMapping
     public ResponseEntity<UserResponse> useAttack(@RequestBody String json){
         jsonParser = new JsonParser();

         int btn = jsonParser.extractButton(json);
         Long heroId = jsonParser.extractHeroId(json);
         Long enemyId = jsonParser.extractEnemyId(json);
         Long battleId = jsonParser.extractBattleId(json);

         logger.info("Inside useAttack");
         Battle battle = battleService.useAttack(btn, heroId, enemyId, battleId);
         try {
             URI location = URI.create("/battle/" + battle.getId());
             logger.info("Location: " + location);
             userResponse = new UserResponse(location, battle.getId());
             logger.info("useAttack api PUT call Response: " + userResponse);
             return ResponseEntity.created(location).body(userResponse);
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
     }

 }
