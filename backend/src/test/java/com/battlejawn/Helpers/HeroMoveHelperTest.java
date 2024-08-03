package com.battlejawn.Helpers;

import com.battlejawn.DTO.HeroMoveDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class HeroMoveHelperTest {
    HeroMoveHelper heroMoveHelper = new HeroMoveHelper();
    List<String> list = new ArrayList<>();
    @Test
    void getHeroMoveReturnObjectTest(){
        HeroMoveDTO heroMoveDTO = heroMoveHelper.getHeroMoveReturnObject(1,1,1,list,false);
        Assertions.assertEquals(heroMoveDTO.getEnemyHealth(), 1);
        Assertions.assertEquals(heroMoveDTO.getHeroHealth(), 1);
        Assertions.assertEquals(heroMoveDTO.getHeroResource(), 1);
        Assertions.assertEquals(heroMoveDTO.getBattleHistory(), list);
        Assertions.assertFalse(heroMoveDTO.isGameOver());
    }
    @Test
    void getDamageMessageTest(){
        String message = heroMoveHelper.getDamageMessage("Move", 1);
        String message2 = heroMoveHelper.getDamageMessage("Move", 0);

        Assertions.assertEquals(message, "Move did 1 damage.");
        Assertions.assertEquals(message2, "Move missed!");
    }
    @Test
    void criticalHitTest(){
        heroMoveHelper.criticalHit(0);
    }
    @Test
    void getDamageMissTest(){
        heroMoveHelper.getDamage(0, 0, 100);
    }
    @Test
    void getDamageHitTest(){
        heroMoveHelper.getDamage(0, 0, 0);
    }
    @Test
    void useRunTest(){
        heroMoveHelper.useRun();
    }
    @Test
    void useStealTest(){
        heroMoveHelper.useSteal();
    }
}
