package com.battlejawn.EnemyMove;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SoulEaterTest {
    @Test
    void attackMissTest() {
        SoulEater soulEater = new SoulEater();

        SoulEater spySoulEater = spy(soulEater);
        when(spySoulEater.miss()).thenReturn(true);

        int damage = spySoulEater.attack();

        assertEquals(spySoulEater.getDamage(), damage);
    }

    @Test
    void attackCriticalHitTest() {
        SoulEater soulEater = new SoulEater();

        SoulEater spySoulEater = spy(soulEater);
        when(spySoulEater.miss()).thenReturn(false);
        when(spySoulEater.criticalHit()).thenReturn(true);

        int damage = spySoulEater.attack();

        assertEquals(spySoulEater.getDamage(), damage);
    }

    @Test
    void attackElseTest() {
        SoulEater soulEater = new SoulEater();

        SoulEater spySoulEater = spy(soulEater);
        when(spySoulEater.miss()).thenReturn(false);
        when(spySoulEater.criticalHit()).thenReturn(false);

        int damage = spySoulEater.attack();

        assertEquals(spySoulEater.getDamage(), damage);
    }
}
