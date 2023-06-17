package com.battlejawn.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.battlejawn.PlayerTip;
import com.battlejawn.Service.PlayerTipService;

@RestController
@RequestMapping("player-tip")
public class PlayerTipController {

    private final PlayerTipService playerTipService;

    @Autowired
    public PlayerTipController(PlayerTipService playerTipService) {
        this.playerTipService = playerTipService;
    }

    @GetMapping("/random")
    public PlayerTip getRandomPlayerTip() {
        return playerTipService.getRandomPlayerTip();
    }

    @GetMapping("/all")
    public List<PlayerTip> getAllPlayerTip() {
        return playerTipService.getAllPlayerTips();
    }

}