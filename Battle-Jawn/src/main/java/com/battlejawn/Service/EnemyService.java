package com.battlejawn.Service;

import org.springframework.stereotype.Service;

import com.battlejawn.Repository.EnemyRepository;

@Service
public class EnemyService {

    private EnemyRepository enemyRepository;

    EnemyService(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
    }
    
}
