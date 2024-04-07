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
    private final CoinProcessorService coinProcessorService;
    private final Logger logger = Logger.getLogger(BattleSessionService.class.getName());
    public String processExperience(Hero hero, Enemy enemy, String battleResult) {
        logger.info("Inside processExperience service method.");

        Long experience = determineExperience(hero.getLevel(), enemy.getLevel(), enemy.getName());
        int initialHeroLevel = hero.getLevel();
        String endOfBattleMessage = "";

        if (battleResult.equals("Hero wins")){
            Long coinsGained = coinProcessorService.processCoins(enemy);
            hero.setCoins(hero.getCoins() + coinsGained);

            hero.setExperience(hero.getExperience() + experience);
            int heroLevelAfterBattle = determineLevel(hero.getExperience());

            if (initialHeroLevel < heroLevelAfterBattle) {
                hero.setLevel(heroLevelAfterBattle);
                endOfBattleMessage = "Congratulations! You win! You've reached level " + heroLevelAfterBattle + "!" +
                        " Enemy dropped " + coinsGained + " coins.";
                hero.setHealth(determineLevelMaxHealth(hero.getLevel(), hero.getRole()));
                hero.setMaxHealth(determineLevelMaxHealth(hero.getLevel(), hero.getRole()));
                hero.setMaxPotions(determineLevelMaxPotions(hero.getLevel(), hero.getRole()));
            } else {
                endOfBattleMessage = "You win! You've gained " + experience + " experience!" +
                        " Enemy dropped " + coinsGained + " coins.";
            }
        } else if (battleResult.equals("Hero loses")){
            Long updatedExperience = calculateExperienceLoss(hero.getExperience(), experience);
            hero.setExperience(updatedExperience);
            endOfBattleMessage = "You've lost " + experience + " experience.";
        } else if (battleResult.equals("Hero runs")) {
            endOfBattleMessage = "You've gained 0 experience.";
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

    private int determineLevelMaxHealth(int level, String role){
            switch(role) {
                case "Tank":
                    return switch (level) {
                        case 2 -> 131;
                        case 3 -> 137;
                        case 4 -> 145;
                        case 5 -> 156;
                        case 6 -> 169;
                        case 7 -> 185;
                        case 8 -> 200;
                        case 9 -> 217;
                        default -> 250;
                    };
                case "Healer":
                    return switch (level) {
                        case 2 -> 111;
                        case 3 -> 117;
                        case 4 -> 125;
                        case 5 -> 136;
                        case 6 -> 149;
                        case 7 -> 165;
                        case 8 -> 185;
                        case 9 -> 210;
                        default -> 230;
                    };
                case "DPS":
                    return switch (level) {
                        case 2 -> 101;
                        case 3 -> 107;
                        case 4 -> 115;
                        case 5 -> 126;
                        case 6 -> 139;
                        case 7 -> 155;
                        case 8 -> 175;
                        case 9 -> 200;
                        default -> 220;
                    };
                case "Caster":
                    return switch (level) {
                        case 2 -> 98;
                        case 3 -> 105;
                        case 4 -> 112;
                        case 5 -> 123;
                        case 6 -> 136;
                        case 7 -> 150;
                        case 8 -> 165;
                        case 9 -> 185;
                        default -> 210;
                    };
            }
        return 0;
    }
    private int determineLevelMaxPotions(int level, String role){
            switch(role) {
                case "Tank":
                    return switch (level) {
                        case 1 -> 3;
                        case 2,3 -> 4;
                        case 4,5 -> 5;
                        case 6,7 -> 6;
                        case 8 -> 7;
                        case 9 -> 8;
                        default -> 10;
                    };
                case "Healer":
                    return 0;
                case "DPS":
                    return switch (level) {
                        case 1,2 -> 3;
                        case 3,4 -> 4;
                        case 5,6 -> 5;
                        case 7,8 -> 6;
                        case 9 -> 7;
                        default -> 9;
                    };
                case "Caster":
                    return switch (level) {
                        case 1,2,3 -> 3;
                        case 4,5,6 -> 4;
                        case 7,8 -> 5;
                        case 9 -> 6;
                        default -> 7;
                    };
            }
        return 0;
    }
}
