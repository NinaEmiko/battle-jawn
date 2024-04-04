package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ExperienceProcessorService {
    private final Logger logger = Logger.getLogger(BattleSessionService.class.getName());
    public String processExperience(Hero hero, Enemy enemy, String battleResult) {
        logger.info("Inside processExperience service method.");

        Long experience = determineExperience(hero.getLevel(), enemy.getLevel(), enemy.getName());
        int initialHeroLevel = hero.getLevel();
        String endOfBattleMessage = "";

        if (battleResult.equals("Hero wins")){
            hero.setExperience(hero.getExperience() + experience);
            endOfBattleMessage = "You won the battle! You've gained " + experience + " experience!";
        } else if (battleResult.equals("Hero loses")){
            Long updatedExperience = calculateExperienceLoss(hero, experience);
            hero.setExperience(updatedExperience);
            endOfBattleMessage = "You lost the battle! You've lost " + updatedExperience + " experience.";
        }
        determineLevel(hero);
        int heroLevelAfterBattle = hero.getLevel();
        if (initialHeroLevel < heroLevelAfterBattle) {
            endOfBattleMessage = "Congratulations! You've reached level " + heroLevelAfterBattle + "!";
        }
        return endOfBattleMessage;
    }

    private Long determineExperience(int heroLevel, int enemyLevel, String enemyName) {
        if (enemyLevel < heroLevel - 3) {
            return 0L;
        } else {
            return switch (enemyName) {
                case "Wolf" -> determineExperienceWolf(enemyLevel);
                case "Orc" -> determineExperienceOrc(enemyLevel);
                case "Spirit" -> determineExperienceSpirit(enemyLevel);
                default -> determineExperienceThief(enemyLevel);
            };
        }
    }

    private Long determineExperienceWolf(int enemyLevel) {
        return switch (enemyLevel) {
            case 1 -> 15L;
            case 2 -> 17L;
            case 3 -> 20L;
            case 4 -> 24L;
            case 5 -> 29L;
            case 6 -> 35L;
            case 7 -> 42L;
            case 8 -> 49L;
            case 9 -> 60L;
            default -> 72L;
        };
    }
    private Long determineExperienceSpirit(int enemyLevel) {
        return switch (enemyLevel) {
            case 1 -> 20L;
            case 2 -> 23L;
            case 3 -> 27L;
            case 4 -> 32L;
            case 5 -> 38L;
            case 6 -> 45L;
            case 7 -> 53L;
            case 8 -> 62L;
            case 9 -> 72L;
            default -> 83L;
        };
    }
    private Long determineExperienceOrc(int enemyLevel) {
        return switch (enemyLevel) {
            case 1 -> 17L;
            case 2 -> 20L;
            case 3 -> 24L;
            case 4 -> 28L;
            case 5 -> 34L;
            case 6 -> 40L;
            case 7 -> 47L;
            case 8 -> 56L;
            case 9 -> 66L;
            default -> 79L;
        };
    }
    private Long determineExperienceThief(int enemyLevel) {
        return switch (enemyLevel) {
            case 1 -> 17L;
            case 2 -> 20L;
            case 3 -> 24L;
            case 4 -> 28L;
            case 5 -> 34L;
            case 6 -> 40L;
            case 7 -> 47L;
            case 8 -> 56L;
            case 9 -> 66L;
            default -> 79L;
        };
    }

    private void determineLevel(Hero hero) {
        if (hero.getExperience() > 5000L) {
            hero.setLevel(10);
        } else if (hero.getExperience() > 3000L) {
            hero.setLevel(9);
        } else if (hero.getExperience() > 2000L) {
            hero.setLevel(8);
        } else if (hero.getExperience() > 1250L) {
            hero.setLevel(7);
        } else if (hero.getExperience() > 750L) {
            hero.setLevel(6);
        } else if (hero.getExperience() > 500L) {
            hero.setLevel(5);
        } else if (hero.getExperience() > 300L) {
            hero.setLevel(4);
        } else if (hero.getExperience() > 125L) {
            hero.setLevel(3);
        } else if (hero.getExperience() > 50L) {
            hero.setLevel(2);
        }
    }

    private Long calculateExperienceLoss(Hero hero, Long experience){
        if (hero.getExperience() > 5000L) {
            return Math.max(hero.getExperience() - experience, 5000L);
        } else if (hero.getExperience() > 3000L) {
            return Math.max(hero.getExperience() - experience, 3000L);
        } else if (hero.getExperience() > 2000L) {
            return Math.max(hero.getExperience() - experience, 2000L);
        } else if (hero.getExperience() > 1250L) {
            return Math.max(hero.getExperience() - experience, 1250L);
        } else if (hero.getExperience() > 750L) {
            return Math.max(hero.getExperience() - experience, 750L);
        } else if (hero.getExperience() > 500L) {
            return Math.max(hero.getExperience() - experience, 500L);
        } else if (hero.getExperience() > 300L) {
            return Math.max(hero.getExperience() - experience, 300L);
        } else if (hero.getExperience() > 125L) {
            return Math.max(hero.getExperience() - experience, 125L);
        } else if (hero.getExperience() > 50L) {
            return Math.max(hero.getExperience() - experience, 50L);
        } else {
            return Math.max(hero.getExperience() - experience, 0L);
        }

    }
}
