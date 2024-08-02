package com.battlejawn.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class HeroMoveDTOTest {

    @Test
    void pojoTest(){
        ArrayList<String> list = new ArrayList<>();
        HeroMoveDTO heroMoveDTO = new HeroMoveDTO();
        heroMoveDTO.setEnemyHealth(1);
        heroMoveDTO.setHeroHealth(1);
        heroMoveDTO.setHeroResource(1);
        heroMoveDTO.setBattleHistory(list);
        heroMoveDTO.setPotionCount(1);
        heroMoveDTO.setGameOver(false);
        Assertions.assertEquals(heroMoveDTO.getEnemyHealth(), 1);
        Assertions.assertEquals(heroMoveDTO.getHeroHealth(), 1);
        Assertions.assertEquals(heroMoveDTO.getHeroResource(), 1);
        Assertions.assertEquals(heroMoveDTO.getBattleHistory(), list);
        Assertions.assertEquals(heroMoveDTO.getPotionCount(), 1);
        Assertions.assertFalse(heroMoveDTO.isGameOver());

    }
}
