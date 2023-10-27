package com.battlejawn.Controllers;

import java.net.URI;
import java.util.logging.Logger;
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
import com.battlejawn.Entities.Hero.Toon;
import com.battlejawn.Service.ToonService;

@RestController
@RequestMapping("/api/toon")
public class ToonController {

    private final ToonService toonService;
    private final Logger logger = Logger.getLogger(ToonController.class.getName());
    private JsonParser jsonParser;
    private UserResponse userResponse;

    @Autowired
    public ToonController(ToonService toonService){
        this.toonService = toonService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createNewToon(@RequestBody String role) {
        logger.info("Inside createNewToon");
        jsonParser = new JsonParser();
        String parsedRole = jsonParser.extractRole(role);
        Toon toon = toonService.saveToon(parsedRole);
        logger.info("Role format: " + parsedRole);
        if (toon != null) {
            URI location = URI.create("/toon/" + toon.getId());
            logger.info("Location: " + location);
            userResponse = new UserResponse(location, toon.getId());
            logger.info("addToon api POST call Response: " + userResponse);
            return ResponseEntity.created(location).body(userResponse);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Toon> getToonById(@PathVariable("id") Long id) {
        logger.info("Inside Toon Controller ID: " + id);
        Toon toon = toonService.getToonById(id);

        if (toon != null) {
            return new ResponseEntity<>(toon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/health/{id}")
    public ResponseEntity<Integer> getHealthById(@PathVariable("id") Long id) {
        logger.info("Inside getHealthById");
        Integer currentHealth = toonService.getToonHealthById(id);
        logger.info("Toon health: " + currentHealth);
        if (currentHealth != null) {
            return new ResponseEntity<>(currentHealth, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
