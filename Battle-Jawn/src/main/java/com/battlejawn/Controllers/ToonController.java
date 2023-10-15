package com.battlejawn.Controllers;

import java.net.URI;
import java.util.List;
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
import com.battlejawn.Entities.Roles.Toon;
import com.battlejawn.Service.ToonService;

@RestController
@RequestMapping("/api/toon")
public class ToonController {

    private ToonService toonService;
    private Logger logger = Logger.getLogger(ToonController.class.getName());
    private JsonParser jsonParser;

    @Autowired
    public ToonController(ToonService toonService){
        this.toonService = toonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Toon>> getAllToons() {
        List<Toon> toons = toonService.getAllToons();
        return new ResponseEntity<>(toons, HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<String> getNewestToon() {
        List<Toon> toonList = toonService.getAllToons();
        Toon toon = toonService.getToonById((long) toonList.size());
        logger.info("Role: " + toon.getRole());
        return new ResponseEntity<String>(toon.getRole(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addToon(@RequestBody String role) {
        jsonParser = new JsonParser();
        String parsedRole = jsonParser.extractJson(role);
        Toon toon = toonService.saveToon(parsedRole);
        logger.info("Role format: " + parsedRole);
        if (toon != null) {
            URI location = URI.create("/toon/" + toon.getId());
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Toon> getToonById(@PathVariable Long id) {
        Toon toon = toonService.getToonById(id);

        if (toon != null) {
            return new ResponseEntity<>(toon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
