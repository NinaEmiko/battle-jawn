package com.battlejawn.Service;

import com.battlejawn.Entities.Hero.Hero;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class StoreService {
    private final HeroService heroService;
    private final Logger logger = Logger.getLogger(StoreService.class.getName());

    public String buy(Long heroId, String item, int quantity) {
        logger.info("Inside buy service method");
        Hero hero = heroService.getHeroById(heroId);
        if (item.equals("potion")) {
            if (hero.getCoins() - (2L * quantity) < 0) {
                return "Insufficient coins.";
            } else {
                hero.setPotions(hero.getPotions() + quantity);
                hero.setCoins(hero.getCoins() - (2L * quantity));
                heroService.updateHero(hero);
                return "You purchased " + quantity + "potions";
            }
        }
        return "There was a problem processing your purchase. Please try again.";
    }
}
