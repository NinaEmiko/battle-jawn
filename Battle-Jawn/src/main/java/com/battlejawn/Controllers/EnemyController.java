package com.battlejawn.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battlejawn.Service.EnemyService;

@RestController
@RequestMapping("/api/enemy")
public class EnemyController {

    private EnemyService enemyService;

    @Autowired
    public EnemyController(EnemyService enemyService) {
        this.enemyService = enemyService;
    }
    
}
