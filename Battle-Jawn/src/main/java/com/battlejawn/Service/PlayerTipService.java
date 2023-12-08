package com.battlejawn.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Controllers.HeroController;
import com.battlejawn.Entities.PlayerTip;
import com.battlejawn.Repository.PlayerTipRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.EntityNotFoundException;

@Service
public class PlayerTipService {

    private final PlayerTipRepository playerTipRepository;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());

    @Autowired
    public PlayerTipService(PlayerTipRepository playerTipRepository) {
        this.playerTipRepository = playerTipRepository;
    }

    public String getRandomPlayerTip() {
        logger.info("Inside getRandomPlayerTip service method.");
        List<PlayerTip> playerTip = playerTipRepository.findAll();
        int randomIndex = new Random().nextInt(playerTip.size());
        PlayerTip randomPlayerTip = playerTip.get(randomIndex);
        return randomPlayerTip.getBody();
    }

    @GetMapping("/{id}")
    public PlayerTip getPlayerTipById(@PathVariable long id) {
        logger.info("Inside getPlayerTipById service method.");
        Optional<PlayerTip> playerTip = playerTipRepository.findById(id);
        if (playerTip.isPresent()) {
            return playerTip.get();
        } else {
            throw new EntityNotFoundException("Player Tip with ID " + id + " not found.");
        }
    }

    public PlayerTip savePlayerTip(PlayerTip tip) {
        logger.info("Inside savePlayerTip service method. Tip: " + tip + ".");
        return playerTipRepository.save(tip);
    }

    public List<PlayerTip> getAllPlayerTips() {
        logger.info("Inside getAllPlayerTip service method.");
        return playerTipRepository.findAll();
    }

    public void deletePlayerTip(Long id) {
        logger.info("Inside deletePlayerTip service method. Player tip ID: " + id + ".");
        playerTipRepository.deleteById(id);
    }
}