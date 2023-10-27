 package com.battlejawn.Controllers;

 import com.battlejawn.Entities.Enemy.Enemy;
 import com.battlejawn.Service.AttackService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 import java.util.logging.Logger;

 @RestController
 @RequestMapping("/api/attack")
 public class AttackController {

     private AttackService attackService;
     private final Logger logger = Logger.getLogger(AttackController.class.getName());

     @Autowired
     public AttackController(AttackService attackService) {
         this.attackService = attackService;
     }

     @PostMapping
     public ResponseEntity<Enemy> playerAttack(@RequestBody Long toonId) {
         logger.info("Inside playerAttack");
//         attackService.perform(0, toonId, null, null);
        

         // if (null != null) {
         //     URI location = URI.create("/attack/" + enemy.getId());
         //     logger.info("Location: " + location);
         //     logger.info("Player Attack data: " + enemy);
         //     return ResponseEntity.created(location).body(enemy);
         // } else {
         //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         // }
         return null;
     }
    
 }
