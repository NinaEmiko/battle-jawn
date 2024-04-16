package com.battlejawn.HeroMove.Heal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class HealTest {
    @Test
    void healCriticalHitTest() {
        Heal heal = new Heal();
        Heal spyHeal = spy(heal);
        when(spyHeal.criticalHit()).thenReturn(true);
        int healAmount = spyHeal.useHeal();
        Assertions.assertTrue(healAmount > 0);
    }
    @Test
    void healCriticalHitFalseTest() {
        Heal heal = new Heal();
        Heal spyHeal = spy(heal);
        when(spyHeal.criticalHit()).thenReturn(false);
        int healAmount = spyHeal.useHeal();
        Assertions.assertTrue(healAmount > 0);
    }
}
