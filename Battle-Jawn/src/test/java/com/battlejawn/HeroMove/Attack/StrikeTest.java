package com.battlejawn.HeroMove.Attack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class StrikeTest {
    @Test
    void attackMissTest() {
        Strike strike = new Strike();

        Strike spyStrike = spy(strike);
        when(spyStrike.miss()).thenReturn(true);

        int damage = spyStrike.attack();

        assertEquals(0, damage);
    }

    @Test
    void attackCriticalHitTest() {
        Strike strike = new Strike();

        Strike spyStrike = spy(strike);
        when(spyStrike.miss()).thenReturn(false);
        when(spyStrike.criticalHit()).thenReturn(true);

        int damage = spyStrike.attack();

        assertTrue(damage > 0);
    }

    @Test
    void attackElseTest() {
        Strike strike = new Strike();

        Strike spyStrike = spy(strike);
        when(spyStrike.miss()).thenReturn(false);
        when(spyStrike.criticalHit()).thenReturn(false);

        int damage = spyStrike.attack();

        assertTrue(damage > 0);
    }
}
