package com.battlejawn.HeroMove.Attack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class WandTest {
    @Test
    void attackMissTest() {
        Wand wand = new Wand();

        Wand spyWand = spy(wand);
        when(spyWand.miss()).thenReturn(true);

        int damage = spyWand.attack();

        assertEquals(damage, spyWand.getDamage());
    }

    @Test
    void attackElseTest() {
        Wand wand = new Wand();

        Wand spyWand = spy(wand);
        when(spyWand.miss()).thenReturn(false);

        int damage = spyWand.attack();

        assertEquals(damage, spyWand.getDamage());
    }
}
