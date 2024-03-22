package com.battlejawn.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

import com.battlejawn.Config.AppException;
import com.battlejawn.Entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.battlejawn.Controllers.HeroController;
import com.battlejawn.Entities.PlayerTip;
import com.battlejawn.Repository.PlayerTipRepository;
import org.springframework.transaction.annotation.Transactional;
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

    public List<PlayerTip> getAllPlayerTips() {
        logger.info("Inside getAllPlayerTip service method.");
        return playerTipRepository.findAll();
    }

    public PlayerTip getPlayerTipById(long id) {
        logger.info("Inside getPlayerTipById service method.");
        Optional<PlayerTip> playerTip = playerTipRepository.findById(id);
        if (playerTip.isPresent()) {
            return playerTip.get();
        } else {
            throw new EntityNotFoundException("Player Tip with ID " + id + " not found.");
        }
    }

    public String getRandomPlayerTip() {
        logger.info("Inside getRandomPlayerTip service method.");
        List<PlayerTip> playerTip = playerTipRepository.findAll();
        int randomIndex = new Random().nextInt(playerTip.size());
        PlayerTip randomPlayerTip = playerTip.get(randomIndex);
        return randomPlayerTip.getBody();
    }

    public PlayerTip savePlayerTip(String body) {
        PlayerTip playerTip = new PlayerTip();
        playerTip.setBody(body);
        logger.info("Inside savePlayerTip service method. Tip: " + body + ".");

        Optional<PlayerTip> optionalPlayerTip = playerTipRepository.findByBody(body);

        if (optionalPlayerTip.isPresent()) {
            throw new AppException("Tip already exists", HttpStatus.BAD_REQUEST);
        }

        return playerTipRepository.save(playerTip);
    }

    @Transactional
    public void updatePlayerTip(long id, String newBody) {
        logger.info("Inside updatePlayerTip service method. ID: " + id + ".");
        playerTipRepository.updatePlayerTipBodyById(newBody, id);
    }

    @Transactional
    public String deletePlayerTip(Long id) {
        logger.info("Inside deletePlayerTipById service method. ID: " + id);
        Optional<PlayerTip> optionalPlayerTip = playerTipRepository.findById(id);

        if (optionalPlayerTip.isPresent()) {
            playerTipRepository.deleteById(id);
            return "Player Tip with ID " + id + " not found.";
        } else {
            return "Player Tip with ID " + id + " not found.";
        }
    }

}