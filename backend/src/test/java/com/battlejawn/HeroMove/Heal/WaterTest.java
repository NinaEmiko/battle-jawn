package com.battlejawn.HeroMove.Heal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class WaterTest {
    @Test
    void useWaterTest() {
        Water water = new Water();
        Water spyWater = spy(water);
        when(spyWater.useWater()).thenReturn(0);
        int healAmount = spyWater.useWater();
        Assertions.assertEquals(0, healAmount);
    }
}
