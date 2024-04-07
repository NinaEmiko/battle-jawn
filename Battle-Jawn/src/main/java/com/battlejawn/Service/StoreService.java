package com.battlejawn.Service;

import com.battlejawn.Controllers.StoreController;
import com.battlejawn.Entities.Hero.Hero;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class StoreService {
    private final HeroService heroService;
    private final Logger logger = Logger.getLogger(StoreService.class.getName());

    public void buy(Long heroId, String item, int quantity) {
        logger.info("Inside buy service method");
        Hero hero = heroService.getHeroById(heroId);
        if (item.equals("potion")) {
            hero.setPotions(hero.getPotions() + quantity);
            hero.setCoins(hero.getCoins() - (2L * quantity));
            heroService.updateHero(hero);
        }
    }
}
