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
            if (hero.getCoins() - (quantity) < 0) {
                return "Insufficient coins.";
            } else if(hero.getPotions() + quantity > hero.getMaxPotions()) {
                return "You cannot hold that many potions";
            }else {
                hero.setPotions(hero.getPotions() + quantity);
                hero.setCoins(hero.getCoins() - (quantity));
                heroService.updateHero(hero);
                switch (quantity) {
                    case 1: return "You purchased " + quantity + " potion";
                    case 2: return "You purchased " + quantity + " potions";
                }
            }
        }
        return "There was a problem processing your purchase. Please try again.";
    }
}
