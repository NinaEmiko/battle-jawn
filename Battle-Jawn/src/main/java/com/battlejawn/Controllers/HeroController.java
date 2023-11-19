package com.battlejawn.Controllers;

import java.net.URI;
import java.util.logging.Logger;

import com.battlejawn.Service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battlejawn.Config.JsonParser;
import com.battlejawn.Config.UserResponse;
import com.battlejawn.Entities.Hero.Hero;

@RestController
@RequestMapping("/api/hero")
public class HeroController {

    private final HeroService heroService;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());
    private JsonParser jsonParser;
    private UserResponse userResponse;

    @Autowired
    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createNewHero(@RequestBody String role) {
        logger.info("Inside createNewHero");
        jsonParser = new JsonParser();
        String parsedRole = jsonParser.extractRole(role);
        Hero hero = heroService.saveHero(parsedRole);
        logger.info("Role format: " + parsedRole);
        if (hero != null) {
            URI location = URI.create("/hero/" + hero.getId());
            logger.info("Location: " + location);
            userResponse = new UserResponse(location, hero.getId());
            logger.info("addHero api POST call Response: " + userResponse);
            return ResponseEntity.created(location).body(userResponse);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable("id") Long id) {
        logger.info("Inside Hero Controller ID: " + id);
        Hero hero = heroService.getHeroById(id);

        if (hero != null) {
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/health/{id}")
    public ResponseEntity<Integer> getHealthById(@PathVariable("id") Long id) {
        logger.info("Inside getHealthById");
        Integer currentHealth = heroService.getHeroHealthById(id);
        logger.info("Hero health: " + currentHealth);
        if (currentHealth != null) {
            return new ResponseEntity<>(currentHealth, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
