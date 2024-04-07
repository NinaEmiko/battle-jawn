package com.battlejawn.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityNotFoundException;
import com.battlejawn.Entities.UserAccount;
import com.battlejawn.Repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.battlejawn.Controllers.HeroController;
import com.battlejawn.Entities.Hero.Caster;
import com.battlejawn.Entities.Hero.DPS;
import com.battlejawn.Entities.Hero.Healer;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Repository.HeroRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final UserAccountRepository userAccountRepository;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());

    public Hero getHeroById(Long id){
        logger.info("Inside getHeroById service method. Hero ID: " + id + ".");
        Optional<Hero> hero = heroRepository.findById(id);
        if (hero.isPresent()) {
            return hero.get();
        } else {
            throw new EntityNotFoundException("Hero with ID " + id + " not found.");
        }
    }

    public List<Hero> getAllHeroes(){
        logger.info("Inside getAllHeroes service method.");
        List<Hero> heroes = heroRepository.findAll();
        if (!heroes.isEmpty()) {
            return heroes;
        } else {
            throw new EntityNotFoundException("Error fetching heroes.");
        }
    }

    public Integer getHeroHealthById(Long id){
        logger.info("Inside getHeroHealthById service method. Hero ID: " + id);
        Optional<Hero> hero = heroRepository.findById(id);
        if (hero.isPresent()) {
            return hero.get().getHealth();
        } else {
            throw new EntityNotFoundException("Hero with ID " + id + " not found.");
        }
    }
    @Transactional
    public Hero restHeroById(Long id) {
        logger.info("Inside restHeroById service method. Hero ID: " + id);
        Optional<Hero> hero = heroRepository.findById(id);
        if (hero.isPresent()) {
            Hero actualHero = hero.get();
            actualHero.setHealth(actualHero.getMaxHealth());
            updateHero(actualHero);
        }
        return null;
    }

    public List<Hero> getHeroListByAccountId(Long id) {
        logger.info("Inside getHeroListByAccountId service method. User Account ID: " + id);
        List<Hero> heroList = heroRepository.findByUserAccountId(id);
        if (heroList != null) {
            return heroList;
        } else {
            throw new EntityNotFoundException("Hero List with User Account ID " + id + " not found.");
        }
    }

    public List<Hero> getHeroListByWinCount() {
        logger.info("Inside getHeroListByWinCount service method.");
        List<Hero> heroList = heroRepository.findByWinCount();
        if (heroList != null) {
            return heroList;
        } else {
            throw new EntityNotFoundException("Error returning hero list by win count.");
        }
    }
    @Transactional
    public String deleteHeroById(Long id) {
        if (heroRepository.existsById(id)) {
            heroRepository.deleteById(id);
            return "Hero successfully deleted.";
        } else {
            throw new EntityNotFoundException("Hero with ID " + id + " not found");
        }
    }

    @Transactional
    public void updateHero(Hero hero){
        logger.info("Inside save service method. Hero ID: " + hero + ".");
        heroRepository.save(hero);
    }

    @Transactional
    public Hero saveHero(String role, String name, Long userAccountId) {
        try {
            Hero hero = switch (role) {
                case "Tank" -> new Tank(name);
                case "DPS" -> new DPS(name);
                case "Caster" -> new Caster(name);
                default -> new Healer(name);
            };
            Optional<UserAccount> userAccount = userAccountRepository.findById(userAccountId);
            logger.info("Inside saveHero Service Method. User Account: " + userAccount);
            userAccount.ifPresent(hero::setUserAccount);
            heroRepository.save(hero);
            return hero;
        } catch(Exception e) {
            throw new RuntimeException("Failed to save hero: " + e.getMessage() + ".");
        }
    }
}
