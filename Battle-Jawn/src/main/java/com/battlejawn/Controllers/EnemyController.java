package com.battlejawn.Controllers;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battlejawn.Config.UserResponse;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Service.EnemyService;

@RestController
@RequestMapping("/api/enemy")
public class EnemyController {

    private final EnemyService enemyService;
    private Logger logger = Logger.getLogger(ToonController.class.getName());

    @Autowired
    public EnemyController(EnemyService enemyService) {
        this.enemyService = enemyService;
    }

    @GetMapping("/all")
    public List<Enemy> getAllEnemies() {
        logger.info("Inside getAllEnemies Controller method");
        return enemyService.getAllEnemies();
    }

    @PostMapping
    public ResponseEntity<Enemy> createNewEnemy() {
        logger.info("Inside createNewEnemy");
        Enemy enemy = enemyService.createNewEnemy();
        if (enemy != null) {
            URI location = URI.create("/enemy/" + enemy.getId());
            logger.info("Location: " + location);
            logger.info("Enemy data: " + enemy);
            return ResponseEntity.created(location).body(enemy);
            // return new ResponseEntity<>(enemy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
