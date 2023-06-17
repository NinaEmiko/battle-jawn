package com.battlejawn.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battlejawn.PlayerTips;
import com.battlejawn.Service.PlayerTipsService;

@RestController
public class PlayerTipsController {

    private final PlayerTipsService playerTipsService;

    public PlayerTipsController(PlayerTipsService playerTipsService) {
        this.playerTipsService = playerTipsService;
    }

    @GetMapping("/player-tips/random")
    public PlayerTips getRandomPlayerTip() {
        return playerTipsService.getRandomPlayerTip();
    }
    
}