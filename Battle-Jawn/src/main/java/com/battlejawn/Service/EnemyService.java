package com.battlejawn.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.EnemyRepository;

@Service
public class EnemyService {

    private EnemyRepository enemyRepository;

    @Autowired
    public EnemyService(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
    }

    public List<Enemy> getAllEnemies() {
        return enemyRepository.findAll();
    }
    
}
