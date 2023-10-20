package com.battlejawn.Service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battlejawn.Controllers.ToonController;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.EnemyRepository;

@Service
public class EnemyService {

    private EnemyRepository enemyRepository;
    private Logger logger = Logger.getLogger(ToonController.class.getName());

    @Autowired
    public EnemyService(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
    }

    public List<Enemy> getAllEnemies() {
        logger.info("Inside getAllEnemies Service method");
        return enemyRepository.findAll();
    }
    
}
