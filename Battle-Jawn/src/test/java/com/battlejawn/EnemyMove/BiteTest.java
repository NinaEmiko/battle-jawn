package com.battlejawn.EnemyMove;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BiteTest {
    @Test
    void attackMissTest() {
        Bite bite = new Bite();

        Bite spyBite = spy(bite);
        when(spyBite.miss()).thenReturn(true);

        int damage = spyBite.attack();

        assertEquals(spyBite.getDamage(), damage);
    }

    @Test
    void attackCriticalHitTest() {
        Bite bite = new Bite();

        Bite spyBite = spy(bite);
        when(spyBite.miss()).thenReturn(false);
        when(spyBite.criticalHit()).thenReturn(true);

        int damage = spyBite.attack();

        assertEquals(spyBite.getDamage(), damage);
    }

    @Test
    void attackElseTest() {
        Bite bite = new Bite();

        Bite spyBite = spy(bite);
        when(spyBite.miss()).thenReturn(false);
        when(spyBite.criticalHit()).thenReturn(false);

        int damage = spyBite.attack();

        assertEquals(spyBite.getDamage(), damage);
    }
}
