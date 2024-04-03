package com.battlejawn.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityNotFoundException;

import com.battlejawn.Randomizer.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Controllers.HeroController;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Orc;
import com.battlejawn.Entities.Enemy.Spirit;
import com.battlejawn.Entities.Enemy.Thief;
import com.battlejawn.Entities.Enemy.Wolf;
import com.battlejawn.Repository.EnemyRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnemyService {

    private final EnemyRepository enemyRepository;
    private final Randomizer randomizer;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());

    @Autowired
    public EnemyService(EnemyRepository enemyRepository, Randomizer randomizer) {
        this.enemyRepository = enemyRepository;
        this.randomizer = randomizer;
    }

    public List<Enemy> getAllEnemies() {
        logger.info("Inside getAllEnemies service method.");
        return enemyRepository.findAll();
    }

    public Enemy getEnemyById(Long id) {
        logger.info("Inside getEnemyById service method. Enemy ID: " + id + ".");
        Optional<Enemy> enemy = enemyRepository.findById(id);
        if (enemy.isPresent()) {
            return enemy.get();
        } else {
            throw new EntityNotFoundException("Enemy with ID " + id + " not found.");
        }
    }

    public Integer getEnemyHealthById(Long id){
        logger.info("Inside getEnemyHealthById service method. Enemy ID: " + id + ".");
        Optional<Enemy> enemy = enemyRepository.findById(id);
        if (enemy.isPresent()) {
            return enemy.get().getHealth();
        } else {
            throw new EntityNotFoundException("Enemy with ID " + id + " not found.");
        }
    }
    @Transactional
    public void updatePotionCountById(int updatedPotionCount, Long enemyId) {
        logger.info("Inside updatePotionCountById service method. Enemy ID: " + enemyId + ". Updated potion count: " + updatedPotionCount + ".");
        enemyRepository.updatePotionCountById(updatedPotionCount, enemyId);
    }
    @Transactional
    public void updateHealthById(int updatedEnemyHealth, Long enemyId) {
        logger.info("Inside updateHealthById service method. Enemy ID: " + enemyId + ". Updated Enemy Health: " + updatedEnemyHealth + ".");
        enemyRepository.updateHealthById(updatedEnemyHealth, enemyId);
    }

    @Transactional
    public Enemy createNewEnemy(int level) {
        try {
            Enemy enemy;
            logger.info("Inside createNewEnemy service method.");
            int randomIndex = randomizer.getRandomInt(4);
            int randomLevel = randomizer.getRandomInt(level);

            switch (randomIndex) {
                case 1: enemy = new Orc(randomLevel);
                        logger.info("New Orc created: " + enemy + ".");
                        enemyRepository.save(enemy);
                        return enemy;
                case 2: enemy = new Spirit(randomLevel);
                    logger.info("New Spirit created: " + enemy + ".");
                        enemyRepository.save(enemy);
                        return enemy;
                case 3: enemy = new Thief(randomLevel);
                        logger.info("New Thief created: " + enemy + ".");
                        enemyRepository.save(enemy);
                        return enemy;
                case 4: enemy = new Wolf(randomLevel);
                        logger.info("New Wolf created: " + enemy + ".");
                        enemyRepository.save(enemy);
                        return enemy;
            }
            return null;
        } catch(Exception e) {
            throw new RuntimeException("Failed to save enemy: " + e.getMessage() + ".");
        }
    }
}
