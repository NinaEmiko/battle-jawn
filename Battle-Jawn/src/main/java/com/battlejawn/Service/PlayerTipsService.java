package com.battlejawn.Service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.battlejawn.PlayerTips;
import com.battlejawn.Repository.PlayerTipsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlayerTipsService {
    private final PlayerTipsRepository playerTipsRepository;

    public PlayerTipsService(PlayerTipsRepository playerTipsRepository) {
        this.playerTipsRepository = playerTipsRepository;
    }

    public PlayerTips getRandomPlayerTip() {
        List<PlayerTips> playerTips = playerTipsRepository.findAll();
        int randomIndex = new Random().nextInt(playerTips.size());
        return playerTips.get(randomIndex);
    }

    public PlayerTips savePlayerTip(PlayerTips tip) {
        return playerTipsRepository.save(tip);
    }

    public List<PlayerTips> getAllPlayerTips() {
        return playerTipsRepository.findAll();
    }

    public void deletePlayerTip(Long id) {
        playerTipsRepository.deleteById(id);
    }
}