package com.battlejawn.Controllers;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battlejawn.PlayerTips;
import com.battlejawn.Repository.PlayerTipsRepository;

@RestController
public class PlayerTipsController {

    private final PlayerTipsRepository playerTipsRepository;

    public PlayerTipsController(PlayerTipsRepository playerTipsRepository) {
        this.playerTipsRepository = playerTipsRepository;
    }

    @GetMapping("/player-tips/random")
    public PlayerTips getRandomPlayerTip() {
        List<PlayerTips> playerTips = playerTipsRepository.findAll();
        int randomIndex = new Random().nextInt(playerTips.size());
        return playerTips.get(randomIndex);
    }
    
}