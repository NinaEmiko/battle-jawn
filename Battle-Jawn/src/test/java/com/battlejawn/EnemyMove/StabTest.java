package com.battlejawn.EnemyMove;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class StabTest {
    @Test
    void attackMissTest() {
        Stab stab = new Stab();

        Stab spyStab = spy(stab);
        when(spyStab.miss()).thenReturn(true);

        int damage = spyStab.attack();

        assertEquals(spyStab.getDamage(), damage);
    }

    @Test
    void attackCriticalHitTest() {
        Stab stab = new Stab();

        Stab spyStab = spy(stab);
        when(spyStab.miss()).thenReturn(false);
        when(spyStab.criticalHit()).thenReturn(true);

        int damage = spyStab.attack();

        assertEquals(spyStab.getDamage(), damage);
    }

    @Test
    void attackElseTest() {
        Stab stab = new Stab();

        Stab spyStab = spy(stab);
        when(spyStab.miss()).thenReturn(false);
        when(spyStab.criticalHit()).thenReturn(false);

        int damage = spyStab.attack();

        assertEquals(spyStab.getDamage(), damage);
    }
}
