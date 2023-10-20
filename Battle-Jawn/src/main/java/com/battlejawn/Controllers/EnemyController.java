package com.battlejawn.Controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        logger.info("Inside getAllEnemies Controller");
        return enemyService.getAllEnemies();
    }
    
}
