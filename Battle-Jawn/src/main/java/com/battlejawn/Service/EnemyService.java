package com.battlejawn.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityNotFoundException;

import com.battlejawn.Randomizer.Randomizer;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class EnemyService {

    private final EnemyRepository enemyRepository;
    private final Randomizer randomizer;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());

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
        Optional<Enemy> optionalEnemy = enemyRepository.findById(enemyId);
        if(optionalEnemy.isPresent()){
            Enemy enemy = optionalEnemy.get();
            enemy.setPotions(updatedPotionCount);
            enemyRepository.save(enemy);
        }
    }
    @Transactional
    public void updateHealthById(int updatedEnemyHealth, Long enemyId) {
        logger.info("Inside updateHealthById service method. Enemy ID: " + enemyId + ". Updated Enemy Health: " + updatedEnemyHealth + ".");
        Optional<Enemy> optionalEnemy = enemyRepository.findById(enemyId);
        if(optionalEnemy.isPresent()){
            Enemy enemy = optionalEnemy.get();
            enemy.setHealth(updatedEnemyHealth);
            enemyRepository.save(enemy);
        }
    }

    @Transactional
    public Enemy createNewEnemy(int heroLevel) {
        try {
            Enemy enemy;
            logger.info("Inside createNewEnemy service method.");
            int randomIndex = randomizer.getRandomInt(4);
            int enemyLevel = randomizer.getRandomInt(heroLevel);

            switch (randomIndex) {
                case 1: enemy = createNewOrc(enemyLevel);
                        logger.info("New Orc created: " + enemy + ".");
                        enemyRepository.save(enemy);
                        return enemy;
                case 2: enemy = createNewSpirit(enemyLevel);
                    logger.info("New Spirit created: " + enemy + ".");
                        enemyRepository.save(enemy);
                        return enemy;
                case 3: enemy = createNewThief(enemyLevel);
                        logger.info("New Thief created: " + enemy + ".");
                        enemyRepository.save(enemy);
                        return enemy;
                case 4: enemy = createNewWolf(enemyLevel);
                        logger.info("New Wolf created: " + enemy + ".");
                        enemyRepository.save(enemy);
                        return enemy;
            }
            return null;
        } catch(Exception e) {
            throw new RuntimeException("Failed to save enemy: " + e.getMessage() + ".");
        }
    }

    private Enemy createNewOrc(int enemyLevel) {
        return switch (enemyLevel) {
            case 1 -> new Orc(enemyLevel, 100, 3, 20);
            case 2 -> new Orc(enemyLevel, 105, 3, 25);
            case 3 -> new Orc(enemyLevel, 111, 3, 30);
            case 4 -> new Orc(enemyLevel, 117, 4, 35);
            case 5 -> new Orc(enemyLevel, 124, 4, 40);
            case 6 -> new Orc(enemyLevel, 132, 4, 45);
            case 7 -> new Orc(enemyLevel, 141, 5, 50);
            case 8 -> new Orc(enemyLevel, 150, 5, 55);
            case 9 -> new Orc(enemyLevel, 160, 5, 60);
            default -> new Orc(enemyLevel, 175, 6, 75);
        };
    }

    private Enemy createNewThief(int enemyLevel) {
        return switch (enemyLevel) {
            case 1 -> new Thief(enemyLevel, 90, 2, 4, 17);
            case 2 -> new Thief(enemyLevel, 95, 2, 4, 20);
            case 3 -> new Thief(enemyLevel, 100, 2, 4, 23);
            case 4 -> new Thief(enemyLevel, 105, 3, 5, 26);
            case 5 -> new Thief(enemyLevel, 110, 3, 5, 29);
            case 6 -> new Thief(enemyLevel, 115, 3, 5, 32);
            case 7 -> new Thief(enemyLevel, 120, 4, 6, 35);
            case 8 -> new Thief(enemyLevel, 125, 4, 6, 38);
            case 9 -> new Thief(enemyLevel, 130, 4, 6,  41);
            default -> new Thief(enemyLevel, 140, 5, 6, 45);
        };
    }

    private Enemy createNewWolf(int enemyLevel) {
        return switch (enemyLevel) {
            case 1 -> new Wolf(enemyLevel, 50, 10);
            case 2 -> new Wolf(enemyLevel, 55, 12);
            case 3 -> new Wolf(enemyLevel, 60, 15);
            case 4 -> new Wolf(enemyLevel, 65, 19);
            case 5 -> new Wolf(enemyLevel, 70, 24);
            case 6 -> new Wolf(enemyLevel, 75, 30);
            case 7 -> new Wolf(enemyLevel, 80, 37);
            case 8 -> new Wolf(enemyLevel, 85, 45);
            case 9 -> new Wolf(enemyLevel, 90,  44);
            default -> new Wolf(enemyLevel, 95, 54);
        };
    }

    private Enemy createNewSpirit(int enemyLevel) {
        return switch (enemyLevel) {
            case 1 -> new Spirit(enemyLevel, 150, 20);
            case 2 -> new Spirit(enemyLevel, 157, 25);
            case 3 -> new Spirit(enemyLevel, 165, 30);
            case 4 -> new Spirit(enemyLevel, 175, 35);
            case 5 -> new Spirit(enemyLevel, 187, 40);
            case 6 -> new Spirit(enemyLevel, 200, 45);
            case 7 -> new Spirit(enemyLevel, 215, 50);
            case 8 -> new Spirit(enemyLevel, 233, 55);
            case 9 -> new Spirit(enemyLevel, 263,  60);
            default -> new Spirit(enemyLevel, 290, 65);
        };
    }
}
