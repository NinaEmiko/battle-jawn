package com.battlejawn.Service;

import java.util.List;
import java.util.Random;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battlejawn.Entities.PlayerTip;
import com.battlejawn.Repository.PlayerTipRepository;

@Service
@Transactional
public class PlayerTipService {

    private final PlayerTipRepository playerTipRepository;

    @Autowired
    public PlayerTipService(PlayerTipRepository playerTipRepository) {
        this.playerTipRepository = playerTipRepository;
    }

    public PlayerTip getRandomPlayerTip() {
        List<PlayerTip> playerTip = playerTipRepository.findAll();
        int randomIndex = new Random().nextInt(playerTip.size());
        return playerTip.get(randomIndex);
    }

    public PlayerTip savePlayerTip(PlayerTip tip) {
        return playerTipRepository.save(tip);
    }

    public List<PlayerTip> getAllPlayerTips() {
        return playerTipRepository.findAll();
        // List<PlayerTip> tips = new ArrayList<PlayerTip>();
        // PlayerTip playerTip = new PlayerTip((long) 1, "This is here");
        // tips.add(playerTip);
        // return tips;
    }

    public void deletePlayerTip(Long id) {
        playerTipRepository.deleteById(id);
    }
}