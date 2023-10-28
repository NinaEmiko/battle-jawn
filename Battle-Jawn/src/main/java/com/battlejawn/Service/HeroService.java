package com.battlejawn.Service;

import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.battlejawn.Controllers.HeroController;
import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.Hero.Healer;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Repository.HeroRepository;

@Service
public class HeroService {

    private final HeroRepository heroRepository;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public Hero getHeroById(Long id){
        logger.info("Inside Hero Service ID: " + id);
        Optional<Hero> hero = heroRepository.findById(id);
        if (hero.isPresent()) {
            logger.info("Inside Hero isPresent");
            return hero.get();
        } else {
            throw new EntityNotFoundException("Hero with ID " + id + " not found");
        }
    }

    public Integer getHeroHealthById(Long id){
        logger.info("Inside getHeroHealthById Service. ID: " + id);
        Optional<Hero> hero = heroRepository.findById(id);
        if (hero.isPresent()) {
            logger.info("Inside Hero isPresent");
            return hero.get().getHealth();
        } else {
            throw new EntityNotFoundException("Hero with ID " + id + " not found");
        }
    }

    @Transactional
    public Hero saveHero(String role) {
        try {
            Hero hero = new Healer();

            switch (role) {
                case "Tank":
                    hero = new Tank();
                    break;
                case "DPS":
                    hero = new DPS();
                    break;
                case "Caster":
                    hero = new Caster();
                    break;
            }

            heroRepository.save(hero);
            return hero;
        } catch(Exception e) {
            throw new RuntimeException("Failed to save hero: " + e.getMessage());
        }
    }
}
