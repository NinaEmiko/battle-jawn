package com.battlejawn.HeroMove.StrongAttack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class IceBlastTest {
    @Test
    void fireBlastMissedTest() {
        IceBlast iceBlast = new IceBlast();
        IceBlast spyIceBlast = spy(iceBlast);
        when(spyIceBlast.miss()).thenReturn(true);
        when(spyIceBlast.criticalHit()).thenReturn(false);
        int damage = spyIceBlast.attack();
        Assertions.assertEquals(damage, spyIceBlast.getDamage());
    }
    @Test
    void fireBlastCriticalHitTest() {
        IceBlast iceBlast = new IceBlast();
        IceBlast spyIceBlast = spy(iceBlast);
        when(spyIceBlast.miss()).thenReturn(false);
        when(spyIceBlast.criticalHit()).thenReturn(true);
        int damage = spyIceBlast.attack();
        Assertions.assertEquals(damage, spyIceBlast.getDamage());
    }
    @Test
    void fireBlastCriticalHitFalseTest() {
        IceBlast iceBlast = new IceBlast();
        IceBlast spyIceBlast = spy(iceBlast);
        when(spyIceBlast.miss()).thenReturn(false);
        when(spyIceBlast.criticalHit()).thenReturn(false);
        int damage = spyIceBlast.attack();
        Assertions.assertEquals(damage, spyIceBlast.getDamage());
    }
}