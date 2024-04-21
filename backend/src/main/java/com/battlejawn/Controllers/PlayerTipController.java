package com.battlejawn.Controllers;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.battlejawn.Entities.PlayerTip;
import com.battlejawn.Service.PlayerTipService;

@RestController
@Validated
@RequestMapping("/api/player-tip")
public class PlayerTipController {
    @Autowired
    private final PlayerTipService playerTipService;
    private final Logger logger = Logger.getLogger(PlayerTipController.class.getName());

    public PlayerTipController(PlayerTipService playerTipService) {
        this.playerTipService = playerTipService;
    }

    @GetMapping("/all")
    public List<PlayerTip> getAllPlayerTip() {
        logger.info("Inside getAllPlayerTip controller method.");
        return playerTipService.getAllPlayerTips();
    }

    @GetMapping("/random")
    public String getRandomTip() {
        logger.info("Inside getRandomTip controller method.");
        String randomTip = playerTipService.getRandomPlayerTip();
        logger.info("Current tip: " + randomTip);
        return randomTip;
    }

    @GetMapping("/{id}")
    public PlayerTip getPlayerTipById(@PathVariable long id) {
        logger.info("Inside getPlayerTipById controller method.");
        return playerTipService.getPlayerTipById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<PlayerTip> createNewPlayerTip(@RequestBody @Valid String body) {
        logger.info("Inside createNewPlayerTip controller method. Body: " + body);
        PlayerTip playerTip = new PlayerTip();
        playerTip.setBody(body);
        PlayerTip response = playerTipService.savePlayerTip(playerTip);
        return ResponseEntity.created(URI.create("/player-tip/" + response.getId())).body(response);
    }

    @PutMapping("/update/{id}")
    public void updatePlayerTip(@PathVariable long id, @RequestBody @Valid String newBody) {
        logger.info("Inside updatePlayerTip controller method.");
        playerTipService.updatePlayerTip(id, newBody);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayerTipById(@PathVariable Long id) {
        logger.info("Inside deletePlayerTipById controller method. ID: " + id);
        String response = playerTipService.deletePlayerTip(id);
        return ResponseEntity.ok(response);
    }

}