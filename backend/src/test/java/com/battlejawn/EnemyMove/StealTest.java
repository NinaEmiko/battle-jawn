package com.battlejawn.EnemyMove;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class StealTest {
    @Test
    void stealTrueTest() {
        EnemySteal steal = new EnemySteal();
        EnemySteal spySteal = spy(steal);
        when(spySteal.useSteal()).thenReturn(true);
        boolean stealBool = spySteal.useSteal();
        Assertions.assertTrue(stealBool);
    }
    @Test
    void stealFalseTest() {
        EnemySteal steal = new EnemySteal();
        EnemySteal spySteal = spy(steal);
        when(spySteal.useSteal()).thenReturn(false);
        boolean stealBool = spySteal.useSteal();
        Assertions.assertFalse(stealBool);
    }
}
