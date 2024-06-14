package com.battlejawn.Entity;

import com.battlejawn.Entities.PlayerTip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTipTest {
    @Test
    void constructorTest(){
        PlayerTip playerTip = new PlayerTip(1L, "test");
        assertEquals(playerTip.getBody(), "test");
        assertEquals(playerTip.getId(), 1L);
    }
}
