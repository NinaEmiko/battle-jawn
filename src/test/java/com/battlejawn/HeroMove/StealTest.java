package com.battlejawn.HeroMove;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class StealTest {
    @Test
    void stealTrueTest() {
        Steal steal = new Steal();
        Steal spySteal = spy(steal);
        when(spySteal.useSteal()).thenReturn(true);
        boolean stealBool = spySteal.useSteal();
        Assertions.assertTrue(stealBool);
    }
    @Test
    void stealFalseTest() {
        Steal steal = new Steal();
        Steal spySteal = spy(steal);
        when(spySteal.useSteal()).thenReturn(false);
        boolean stealBool = spySteal.useSteal();
        Assertions.assertFalse(stealBool);
    }
}
