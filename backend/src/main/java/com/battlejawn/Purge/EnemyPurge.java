package com.battlejawn.Purge;

import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Repository.EnemyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class EnemyPurge {
    private final EnemyRepository enemyRepository;
    private final Logger logger = Logger.getLogger(EnemyPurge.class.getName());

    public EnemyPurge(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void purgeEnemy() {
        logger.info("Inside purgeEnemy service class. This job runs every day at 1 am.");
        List<Enemy> enemyList = enemyRepository.findAll();
        Duration timeDifference;
        Duration oneDay = Duration.ofDays(1);
        for (Enemy enemy: enemyList) {
            timeDifference = Duration.between(enemy.getCreatedAt(), LocalDateTime.now());
            try {
            if (timeDifference.compareTo(oneDay) > 0) {
                logger.info("Inside EnemyPurge service class. Purging enemy with ID: " + enemy.getId() + ".");
                enemyRepository.deleteById(enemy.getId());
            }
            }catch(Exception e) {
                throw new RuntimeException("EnemyPurge failed to delete enemy: " + enemy.getId() + ". Error: " + e.getMessage() + ".");
            }
        }
    }
}
