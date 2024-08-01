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
        when(spyStrike.stagger()).thenReturn(false);

        int damage = spyStrike.attack();

        assertEquals(spyStrike.getDamage(), damage);
    }

    @Test
    void attackElseTest() {
        Strike strike = new Strike();

        Strike spyStrike = spy(strike);
        when(spyStrike.miss()).thenReturn(false);
        when(spyStrike.stagger()).thenReturn(false);

        int damage = spyStrike.attack();

        assertEquals(spyStrike.getDamage(), damage);
    }
    @Test
    void attackStaggerTest() {
        Strike strike = new Strike();

        Strike spyStrike = spy(strike);
        when(spyStrike.miss()).thenReturn(false);
        when(spyStrike.stagger()).thenReturn(true);

        int damage = spyStrike.attack();

        assertEquals(spyStrike.getDamage(), damage);
    }
}
