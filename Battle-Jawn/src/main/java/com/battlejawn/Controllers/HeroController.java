package com.battlejawn.Controllers;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import com.battlejawn.Service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.battlejawn.Config.JsonParser;
import com.battlejawn.Config.UserResponse;
import com.battlejawn.Entities.Hero.Hero;

@RestController
@RequestMapping("/api/hero")
public class HeroController {
    @Autowired
    private final HeroService heroService;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createNewHero(@RequestBody String data) {
        logger.info("Inside createNewHero controller method. New Hero Object: " + data + ".");
        JsonParser jsonParser;
        jsonParser = new JsonParser();
        String parsedRole = jsonParser.extractRole(data);
        Long parsedUserAccountId = jsonParser.extractUserAccountId(data);
        logger.info("Inside createNewHero controller method. Parsed User Account Id: " + parsedUserAccountId + ".");
        Hero hero = heroService.saveHero(parsedRole, parsedUserAccountId);
        UserResponse userResponse;
        if (hero != null) {
            URI location = URI.create("/hero/" + hero.getId());
            userResponse = new UserResponse(location, hero.getId());
            logger.info("Inside createNewHero. User response: " + userResponse);
            return ResponseEntity.created(location).body(userResponse);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable("id") Long id) {
        logger.info("Inside getHeroById controller method. Hero ID: " + id + ".");
        Hero hero = heroService.getHeroById(id);
        logger.info("Inside getHeroById controller method. Hero : " + hero + ".");

        if (hero != null) {
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Hero>> getAllHeroes() {
        logger.info("Inside getAllHeroes controller method.");
        List<Hero> heroes = heroService.getAllHeroes();

        if (heroes != null) {
            return new ResponseEntity<>(heroes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/health/{id}")
    public ResponseEntity<Integer> getHealthById(@PathVariable("id") Long id) {
        logger.info("Inside getHealthById controller method. Hero ID: " + id + ".");
        Integer currentHealth = heroService.getHeroHealthById(id);
        if (currentHealth != null) {
            return new ResponseEntity<>(currentHealth, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<Hero>> getHeroListByAccountId(@PathVariable("id") Long id) {
        logger.info("Inside getHeroListByAccountId controller method. User Account ID: " + id + ".");
        List<Hero> heroList = heroService.getHeroListByAccountId(id);
        if (heroList != null) {
            return new ResponseEntity<>(heroList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Hero>> getHeroListByWinCount() {
        logger.info("Inside getHeroListByWinCount controller method.");
        List<Hero> heroList = heroService.getHeroListByWinCount();
        if (heroList != null) {
            return new ResponseEntity<>(heroList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/rest/{id}")
    public ResponseEntity<Hero> restHeroById(@PathVariable("id") Long id) {
        logger.info("Inside restHeroById controller method. Hero ID: " + id + ".");
        Hero hero = heroService.restHeroById(id);
        if (hero != null) {
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
