package com.battlejawn.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battlejawn.Entities.Hero;
import com.battlejawn.Service.HeroService;

@RestController
@RequestMapping("/api/hero")
public class HeroController {

    private HeroService heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hero>> getAllHeroes() {
        List<Hero> heros = heroService.getAllHeroes();
        return new ResponseEntity<>(heros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable Long id) {
        Hero hero = heroService.getHeroById(id);
        
        if (hero != null) {
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addHero(@RequestBody String name, String role, Long accountId) {
        Hero hero = heroService.saveHero(name, role, accountId);

        if (hero != null) {
            URI location = URI.create("/hero/" + hero.getId());
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHeroById(@PathVariable Long id) {
        heroService.deleteHeroById(id);
        if (heroService.getHeroById(id) != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
