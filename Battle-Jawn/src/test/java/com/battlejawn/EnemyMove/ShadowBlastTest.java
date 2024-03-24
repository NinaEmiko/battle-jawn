package com.battlejawn.EnemyMove;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ShadowBlastTest {
    @Test
    void attackMissTest() {
        ShadowBlast shadowBlast = new ShadowBlast();

        ShadowBlast spyShadowBlast = spy(shadowBlast);
        when(spyShadowBlast.miss()).thenReturn(true);

        int damage = spyShadowBlast.attack();

        assertEquals(spyShadowBlast.getDamage(), damage);
    }

    @Test
    void attackCriticalHitTest() {
        ShadowBlast shadowBlast = new ShadowBlast();

        ShadowBlast spyShadowBlast = spy(shadowBlast);
        when(spyShadowBlast.miss()).thenReturn(false);
        when(spyShadowBlast.criticalHit()).thenReturn(true);

        int damage = spyShadowBlast.attack();

        assertEquals(spyShadowBlast.getDamage(), damage);
    }

    @Test
    void attackElseTest() {
        ShadowBlast shadowBlast = new ShadowBlast();

        ShadowBlast spyShadowBlast = spy(shadowBlast);
        when(spyShadowBlast.miss()).thenReturn(false);
        when(spyShadowBlast.criticalHit()).thenReturn(false);

        int damage = spyShadowBlast.attack();

        assertEquals(spyShadowBlast.getDamage(), damage);
    }
}
