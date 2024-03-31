package com.battlejawn.EnemyMove;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ImpaleTest {
    @Test
    void attackCriticalHitTest() {
        Impale impale = new Impale();
        Impale spyImpale = spy(impale);
        when(spyImpale.criticalHit()).thenReturn(true);
        int damage = spyImpale.attack();
        Assertions.assertEquals(damage, spyImpale.getDamage());
    }
    @Test
    void attackCriticalHitFalseTest() {
        Impale impale = new Impale();
        Impale spyImpale = spy(impale);
        when(spyImpale.criticalHit()).thenReturn(false);
        int damage = spyImpale.attack();
        Assertions.assertEquals(damage, spyImpale.getDamage());
    }
}
