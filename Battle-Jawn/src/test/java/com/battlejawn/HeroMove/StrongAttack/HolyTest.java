package com.battlejawn.HeroMove.StrongAttack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class HolyTest {
    @Test
    void attackMissTest() {
        Holy holy = new Holy();
        Holy spyHoly = spy(holy);
        when(spyHoly.miss()).thenReturn(true);
        when(spyHoly.criticalHit()).thenReturn(false);
        int damage = spyHoly.attack();
        Assertions.assertEquals(damage, spyHoly.getDamage());
    }
    @Test
    void attackCriticalHitTest() {
        Holy holy = new Holy();
        Holy spyHoly = spy(holy);
        when(spyHoly.miss()).thenReturn(false);
        when(spyHoly.criticalHit()).thenReturn(true);
        int damage = spyHoly.attack();
        Assertions.assertEquals(damage, spyHoly.getDamage());
    }
    @Test
    void attackCriticalHitFalseTest() {
        Holy holy = new Holy();
        Holy spyHoly = spy(holy);
        when(spyHoly.miss()).thenReturn(false);
        when(spyHoly.criticalHit()).thenReturn(false);
        int damage = spyHoly.attack();
        Assertions.assertEquals(damage, spyHoly.getDamage());
    }
}
