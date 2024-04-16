package com.battlejawn.HeroMove.StrongAttack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ImpaleTest {
    @Test
    void attackMissTest() {
        Impale impale = new Impale();
        Impale spyImpale = spy(impale);
        when(spyImpale.miss()).thenReturn(true);
        when(spyImpale.criticalHit()).thenReturn(false);
        int damage = spyImpale.attack();
        Assertions.assertEquals(damage, spyImpale.getDamage());
    }
    @Test
    void attackCriticalHitTest() {
        Impale impale = new Impale();
        Impale spyImpale = spy(impale);
        when(spyImpale.miss()).thenReturn(false);
        when(spyImpale.criticalHit()).thenReturn(true);
        int damage = spyImpale.attack();
        Assertions.assertEquals(damage, spyImpale.getDamage());
    }
    @Test
    void attackCriticalHitFalseTest() {
        Impale impale = new Impale();
        Impale spyImpale = spy(impale);
        when(spyImpale.miss()).thenReturn(false);
        when(spyImpale.criticalHit()).thenReturn(false);
        int damage = spyImpale.attack();
        Assertions.assertEquals(damage, spyImpale.getDamage());
    }
}
