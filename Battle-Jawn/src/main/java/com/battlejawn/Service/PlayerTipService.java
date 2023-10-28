package com.battlejawn.Service;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Controllers.HeroController;
import com.battlejawn.Entities.PlayerTip;
import com.battlejawn.Repository.PlayerTipRepository;

@Service
public class PlayerTipService {

    private final PlayerTipRepository playerTipRepository;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());

    @Autowired
    public PlayerTipService(PlayerTipRepository playerTipRepository) {
        this.playerTipRepository = playerTipRepository;
    }

    public String getRandomPlayerTip() {
        logger.info("Inside getRandomPlayerTip Service method");
        List<PlayerTip> playerTip = playerTipRepository.findAll();
        int randomIndex = new Random().nextInt(playerTip.size());
        PlayerTip randomPlayerTip = playerTip.get(randomIndex);
        return randomPlayerTip.getBody();
    }

    public PlayerTip savePlayerTip(PlayerTip tip) {
        logger.info("Inside savePlayerTip Service method");
        return playerTipRepository.save(tip);
    }

    public List<PlayerTip> getAllPlayerTips() {
        logger.info("Inside getAllPlayerTip Service method");
        return playerTipRepository.findAll();
    }

    public void deletePlayerTip(Long id) {
        logger.info("Inside deletePlayerTip Service method");
        playerTipRepository.deleteById(id);
    }
}