package com.battlejawn.Controllers;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import com.battlejawn.Service.HeroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.battlejawn.Config.JsonParser;
import com.battlejawn.Config.UserResponse;
import com.battlejawn.Entities.Hero.Hero;

@RestController
@Validated
@RequestMapping("/api/hero")
public class HeroController {
    @Autowired
    private final HeroService heroService;
    private final JsonParser jsonParser;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());

    public HeroController(HeroService heroService, JsonParser jsonParser){
        this.heroService = heroService;
        this.jsonParser = jsonParser;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createNewHero(@Valid @RequestBody String data) {
        logger.info("Inside createNewHero controller method. New Hero Object: " + data + ".");
        String parsedName = jsonParser.extractHeroName(data);
        String parsedRole = jsonParser.extractRole(data);
        Long parsedUserAccountId = jsonParser.extractUserAccountId(data);
        logger.info("Inside createNewHero controller method. Parsed User Account Id: " + parsedUserAccountId + ".");
        Hero hero = heroService.saveHero(parsedRole, parsedName, parsedUserAccountId);
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

    @GetMapping("/list/high-score")
    public ResponseEntity<List<Hero>> getHeroListByHighScore() {
        logger.info("Inside getHeroListByHighScore controller method.");
        List<Hero> heroList = heroService.getHeroListByHighScore();
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHeroById(@PathVariable("id") Long id) {
        logger.info("Inside deleteHeroById controller method. Hero ID: " + id + ".");
        String response = heroService.deleteHeroById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
