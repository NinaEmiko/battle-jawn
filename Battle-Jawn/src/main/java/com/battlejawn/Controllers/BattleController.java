 package com.battlejawn.Controllers;

 import com.battlejawn.Config.JsonParser;
 import com.battlejawn.Config.UserResponse;
 import com.battlejawn.Entities.Battle;
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

     @PostMapping
     public ResponseEntity<Battle> startNewBattle() {
         logger.info("Inside startNewBattle Controller method");
         Battle battle = battleService.startNewBattle();
         if (battle != null) {
             URI location = URI.create("/battle/" + battle.getId());
             logger.info("Location: " + location);
             logger.info("addHero api POST call Response: " + userResponse);
             return ResponseEntity.created(location).body(battle);
         } else {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
     }

     @GetMapping("/{id}")
     public ResponseEntity<Battle> getBattleById(@PathVariable("id") Long id) {
         logger.info("Inside Battle Controller ID: " + id);
         Battle battle = battleService.getBattleById(id);

         if (battle != null) {
             return new ResponseEntity<>(battle, HttpStatus.OK);
         } else {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
