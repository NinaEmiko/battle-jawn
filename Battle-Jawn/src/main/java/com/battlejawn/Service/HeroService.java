package com.battlejawn.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityNotFoundException;

import com.battlejawn.Entities.UserAccount;
import com.battlejawn.Repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HeroService {

    private final HeroRepository heroRepository;
    private final UserAccountRepository userAccountRepository;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());
    @Autowired
    public HeroService(HeroRepository heroRepository, UserAccountRepository userAccountRepository) {
        this.heroRepository = heroRepository;
        this.userAccountRepository = userAccountRepository;
    }

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

//    public List<Hero> getHeroListByAccountId(Long id) {
//        logger.info("Inside getHeroListByAccountId service method. User Account ID: " + id);
//        List<Hero> heroList = heroRepository.findByUserAccountId(id);
//        if (heroList != null) {
//            return heroList;
//        } else {
//            throw new EntityNotFoundException("Hero List with User Account ID " + id + " not found.");
//        }
//    }

    @Transactional
    public void updateHealthById(int updatedHeroHealth, Long heroId) {
        logger.info("Inside updateHealthById service method. Hero ID: " + heroId + ". Updated Hero Health: " + updatedHeroHealth + ".");
        heroRepository.updateHealthById(updatedHeroHealth, heroId);
    }
    @Transactional
    public void updatePotionCountById(int updatedPotionCount, Long heroId) {
        logger.info("Inside updatePotionCountById service method. Hero ID: " + heroId + ". Updated potion count: " + updatedPotionCount + ".");
        heroRepository.updatePotionCountById(updatedPotionCount, heroId);
    }
    @Transactional
    public void updateRunCountById(Long heroId, int updatedRunCount) {
        logger.info("Inside updateRunCountById service method. Hero ID: " + heroId + ". Updated run count: " + updatedRunCount + ".");
        heroRepository.updateRunCountByHeroId(updatedRunCount, heroId);
    }
    @Transactional
    public void updateWinCountById(Long heroId, int updatedWinCount) {
        logger.info("Inside updateWinCountById service method. Hero ID: " + heroId + ". Updated win count: " + updatedWinCount + ".");
        heroRepository.updateWinCountById(updatedWinCount, heroId);
    }

    @Transactional
    public Hero saveHero(String role, Long userAccountId) {
        try {
            Hero hero = switch (role) {
                case "Tank" -> new Tank();
                case "DPS" -> new DPS();
                case "Caster" -> new Caster();
                default -> new Healer();
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
