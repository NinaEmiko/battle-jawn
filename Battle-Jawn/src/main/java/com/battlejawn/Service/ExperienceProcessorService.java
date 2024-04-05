package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ExperienceProcessorService {
    private final HeroService heroService;
    private final Logger logger = Logger.getLogger(BattleSessionService.class.getName());
    public String processExperience(Hero hero, Enemy enemy, String battleResult) {
        logger.info("Inside processExperience service method.");

        Long experience = determineExperience(hero.getLevel(), enemy.getLevel(), enemy.getName());
        int initialHeroLevel = hero.getLevel();
        String endOfBattleMessage = "";

        if (battleResult.equals("Hero wins")){
            hero.setExperience(hero.getExperience() + experience);
            int heroLevelAfterBattle = determineLevel(hero.getExperience());
            if (initialHeroLevel < heroLevelAfterBattle) {
                endOfBattleMessage = "Congratulations! You've reached level " + heroLevelAfterBattle + "!";
            } else {
                endOfBattleMessage = "You've gained " + experience + " experience!";
            }
        } else if (battleResult.equals("Hero loses")){
            Long updatedExperience = calculateExperienceLoss(hero.getExperience(), experience);
            hero.setExperience(updatedExperience);
            endOfBattleMessage = "You've lost " + experience + " experience.";
        }
        heroService.updateHero(hero);
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

    private int determineLevel(Long experience) {
        if (experience >= 5000L) {
            return 10;
        } else if (experience >= 3000L) {
            return 9;
        } else if (experience >= 2000L) {
            return 8;
        } else if (experience >= 1250L) {
            return 7;
        } else if (experience >= 750L) {
            return 6;
        } else if (experience >= 500L) {
            return 5;
        } else if (experience >= 300L) {
            return 4;
        } else if (experience >= 125L) {
            return 3;
        } else if (experience >= 50L) {
            return 2;
        } else {
            return 1;
        }
    }

    private Long calculateExperienceLoss(Long heroExperience, Long experience){
        if (heroExperience > 5000L) {
            return Math.max(heroExperience - experience, 5000L);
        } else if (heroExperience > 3000L) {
            return Math.max(heroExperience - experience, 3000L);
        } else if (heroExperience > 2000L) {
            return Math.max(heroExperience - experience, 2000L);
        } else if (heroExperience > 1250L) {
            return Math.max(heroExperience - experience, 1250L);
        } else if (heroExperience > 750L) {
            return Math.max(heroExperience - experience, 750L);
        } else if (heroExperience > 500L) {
            return Math.max(heroExperience - experience, 500L);
        } else if (heroExperience > 300L) {
            return Math.max(heroExperience - experience, 300L);
        } else if (heroExperience > 125L) {
            return Math.max(heroExperience - experience, 125L);
        } else if (heroExperience > 50L) {
            return Math.max(heroExperience - experience, 50L);
        } else {
            return Math.max(heroExperience - experience, 0L);
        }

    }
}
