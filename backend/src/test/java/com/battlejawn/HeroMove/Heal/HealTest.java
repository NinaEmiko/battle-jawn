package com.battlejawn.HeroMove.Heal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class HealTest {
    @Test
    void healCriticalHitFalseTest() {
        Heal heal = new Heal();
        Heal spyHeal = spy(heal);
        int healAmount = spyHeal.useHeal();
        Assertions.assertTrue(healAmount > 0);
    }
}
