package com.battlejawn.HeroMove.StrongAttack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BackStabTest {
    @Test
    void attackCriticalHitTest() {
        BackStab backStab = new BackStab();
        BackStab spyBackStab = spy(backStab);
        when(spyBackStab.criticalHit()).thenReturn(true);
        int damage = spyBackStab.attack();
        Assertions.assertEquals(damage, spyBackStab.getDamage());
    }
    @Test
    void attackCriticalHitFalseTest() {
        BackStab backStab = new BackStab();
        BackStab spyBackStab = spy(backStab);
        when(spyBackStab.criticalHit()).thenReturn(false);
        int damage = spyBackStab.attack();
        Assertions.assertEquals(damage, spyBackStab.getDamage());
    }
}
