package com.battlejawn.HeroMove.Heal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class PotionTest {
    @Test
    void usePotionTest() {
        Potion potion = new Potion();
        Potion spyPotion = spy(potion);
        when(spyPotion.usePotion()).thenReturn(30);
        int healAmount = spyPotion.usePotion();
        Assertions.assertEquals(30, healAmount);
    }
}
