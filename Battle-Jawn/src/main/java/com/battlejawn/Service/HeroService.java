package com.battlejawn.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Entities.Hero;
import com.battlejawn.Repository.HeroRepository;

@Service
public class HeroService {

    private HeroRepository heroRepository;

    @Autowired
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    public Hero getHeroById(Long id) {
        return heroRepository.getById(id);
    }

    public Hero saveHero(Hero hero){
        return heroRepository.save(hero);
    }

    public void deleteHeroById(Long id) {
        heroRepository.deleteById(id);
    }

}