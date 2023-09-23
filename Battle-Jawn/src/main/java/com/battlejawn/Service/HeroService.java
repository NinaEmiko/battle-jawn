package com.battlejawn.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Entities.Hero;
import com.battlejawn.Entities.Role;
import com.battlejawn.Repository.HeroRepository;

@Service
public class HeroService {

    private HeroRepository heroRepository;
    private AccountService accountService;

    @Autowired
    public HeroService(HeroRepository heroRepository, AccountService accountService) {
        this.heroRepository = heroRepository;
        this.accountService = accountService;
    }

    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    public Hero getHeroById(Long id) {
        return heroRepository.getById(id);
    }

    public Hero saveHero(String name, String role){
        Long jawn = (long) 4;

        Hero hero = new Hero();
        hero.setName(name);
        hero.setLosses(0);
        hero.setAccount(accountService.getAccountById(jawn));
        hero.setWins(0);
        hero.setTotalBattles(0);
        hero.setTimesRanAway(0);
        hero.setCreationDate(LocalDateTime.now());
        switch (role) {
            case "Tank":
                hero.setRole(Role.TANK);
                hero.setStrength(100);
                hero.setPotions(3);
                hero.setMaxPotions(3);
                hero.setHealth(120);
                hero.setMaxHealth(120);
                break;
            case "Healer":
                hero.setRole(Role.HEALER);
                hero.setStrength(90);
                hero.setPotions(0);
                hero.setMaxPotions(0);
                hero.setHealth(100);
                hero.setMaxHealth(100);
                break;
            case "Caster":
                hero.setRole(Role.CASTER);
                hero.setStrength(110);
                hero.setPotions(3);
                hero.setMaxPotions(3);
                hero.setHealth(90);
                hero.setMaxHealth(90);
                break;
            case "DPS":
                hero.setRole(Role.DPS);
                hero.setStrength(110);
                hero.setPotions(3);
                hero.setMaxPotions(4);
                hero.setHealth(90);
                hero.setMaxHealth(90);
                break;
        }
        return heroRepository.save(hero);
    }

    public void deleteHeroById(Long id) {
        heroRepository.deleteById(id);
    }

}