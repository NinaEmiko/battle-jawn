package com.battlejawn.EnemyMove;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class MaimTest {
    @Test
    void attackMissTest() {
        Maim maim = new Maim();

        Maim spyMaim = spy(maim);
        when(spyMaim.miss()).thenReturn(true);

        int damage = spyMaim.attack();

        assertEquals(spyMaim.getDamage(), damage);
    }

    @Test
    void attackCriticalHitTest() {
        Maim maim = new Maim();

        Maim spyMaim = spy(maim);
        when(spyMaim.miss()).thenReturn(false);
        when(spyMaim.criticalHit()).thenReturn(true);

        int damage = spyMaim.attack();

        assertEquals(spyMaim.getDamage(), damage);
    }

    @Test
    void attackElseTest() {
        Maim maim = new Maim();

        Maim spyMaim = spy(maim);
        when(spyMaim.miss()).thenReturn(false);
        when(spyMaim.criticalHit()).thenReturn(false);

        int damage = spyMaim.attack();

        assertEquals(spyMaim.getDamage(), damage);
    }
}
