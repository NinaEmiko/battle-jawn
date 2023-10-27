package com.battlejawn.Purge;

import com.battlejawn.Repository.EnemyRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class EnemyPurge {

    private final EnemyRepository enemyRepository;
    private Logger logger = Logger.getLogger(HeroPurge.class.getName());

    public EnemyPurge(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
    }

    @Scheduled(cron = "0 0 */6 * * ?")
    public void purgeEnemies() {
        logger.info("Inside purgeEnemies");
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
//        enemyRepository.deleteByDateBefore(oneDayAgo);

    }

}
