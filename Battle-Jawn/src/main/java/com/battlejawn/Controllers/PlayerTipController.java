package com.battlejawn.Controllers;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.battlejawn.Entities.PlayerTip;
import com.battlejawn.Service.PlayerTipService;

@RestController
@RequestMapping("/api/player-tip")
public class PlayerTipController {

    private final PlayerTipService playerTipService;
    private Logger logger = Logger.getLogger(PlayerTipController.class.getName());

    @Autowired
    public PlayerTipController(PlayerTipService playerTipService) {
        this.playerTipService = playerTipService;
    }

    @GetMapping("/all")
    public List<PlayerTip> getAllPlayerTip() {
        logger.info("Inside getAllPlayerTip");
        return playerTipService.getAllPlayerTips();
    }

    @GetMapping("/random")
    public String getRandomTip() {
        logger.info("Inside getRandomTip");
        String randomTip = playerTipService.getRandomPlayerTip();
        logger.info("Current tip: " + randomTip);
        return randomTip;
    }

}