package com.battlejawn.Controllers;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Service.EnemyService;

@RestController
@RequestMapping("/api/enemy")
public class EnemyController {

    @Autowired
    private final EnemyService enemyService;
    private final Logger logger = Logger.getLogger(EnemyController.class.getName());
    public EnemyController(EnemyService enemyService) {
        this.enemyService = enemyService;
    }

    @GetMapping("/all")
    public List<Enemy> getAllEnemies() {
        logger.info("Inside getAllEnemies Controller method");
        return enemyService.getAllEnemies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enemy> getEnemyById(@PathVariable("id") Long id) {
        logger.info("Inside getEnemyById controller method. Enemy ID: " + id + ".");
        Enemy enemy = enemyService.getEnemyById(id);

        if (enemy != null) {
            return new ResponseEntity<>(enemy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/health/{id}")
    public ResponseEntity<Integer> getHealthById(@PathVariable("id") Long id) {
        logger.info("Inside getHealthById controller method. Enemy ID: " + id + ".");
        Integer currentHealth = enemyService.getEnemyHealthById(id);
        if (currentHealth != null) {
            return new ResponseEntity<>(currentHealth, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Enemy> createNewEnemy() {
        logger.info("Inside createNewEnemy controller method");
        Enemy enemy = enemyService.createNewEnemy();
        if (enemy != null) {
            URI location = URI.create("/enemy/" + enemy.getId());
            return ResponseEntity.created(location).body(enemy);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
