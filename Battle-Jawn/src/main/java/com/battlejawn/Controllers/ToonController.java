package com.battlejawn.Controllers;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battlejawn.Entities.Roles.Toon;
import com.battlejawn.Service.ToonService;

@RestController
@RequestMapping("/api/toon")
public class ToonController {

    private ToonService toonService;

    @Autowired
    public ToonController(ToonService toonService){
        this.toonService = toonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Toon>> getAllToons() {
        List<Toon> toons = toonService.getAllToons();
        return new ResponseEntity<>(toons, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addToon(@RequestBody String role) {
        Toon toon = toonService.saveToon(role);
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
