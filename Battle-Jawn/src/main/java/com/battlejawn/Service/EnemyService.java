package com.battlejawn.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Controllers.ToonController;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Orc;
import com.battlejawn.Entities.Enemy.Spirit;
import com.battlejawn.Entities.Enemy.Thief;
import com.battlejawn.Entities.Enemy.Wolf;
import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.Hero.Healer;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.Hero.Toon;
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

    @Transactional
    public Enemy createNewEnemy() {
        try {
            Enemy enemy;
            logger.info("Inside createNewEnemy Service method");
            int randomIndex = (int) Math.floor(Math.random() * 3) + 1;
            logger.info("randomIndex: " + randomIndex);

            switch (randomIndex) {
                case 1: enemy = new Orc();
                        logger.info("New Orc created: " + enemy);
                        enemyRepository.save(enemy);
                        return enemy;
                case 2: enemy = new Spirit();
                    logger.info("New Spirit created: " + enemy);
                        enemyRepository.save(enemy);
                        return enemy;
                case 3: enemy = new Thief();
                        logger.info("New Thief created: " + enemy);
                        enemyRepository.save(enemy);
                        return enemy;
                case 4: enemy = new Wolf();
                        logger.info("New Wolf created: " + enemy);
                        enemyRepository.save(enemy);
                        break;
            }
            return null;
        } catch(Exception e) {
            throw new RuntimeException("Failed to save enemy: " + e.getMessage());
        }
    }
    
}
