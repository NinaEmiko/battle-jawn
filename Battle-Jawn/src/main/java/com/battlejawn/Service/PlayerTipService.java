package com.battlejawn.Service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Entities.PlayerTip;
import com.battlejawn.Repository.PlayerTipRepository;

@Service
public class PlayerTipService {

    private final PlayerTipRepository playerTipRepository;

    @Autowired
    public PlayerTipService(PlayerTipRepository playerTipRepository) {
        this.playerTipRepository = playerTipRepository;
    }

    public String getRandomPlayerTip() {
        List<PlayerTip> playerTip = playerTipRepository.findAll();
        int randomIndex = new Random().nextInt(playerTip.size());
        PlayerTip randomPlayerTip = playerTip.get(randomIndex);
        return randomPlayerTip.getBody();
    }

    public PlayerTip savePlayerTip(PlayerTip tip) {
        return playerTipRepository.save(tip);
    }

    public List<PlayerTip> getAllPlayerTips() {
        return playerTipRepository.findAll();
    }

    public void deletePlayerTip(Long id) {
        playerTipRepository.deleteById(id);
    }
}