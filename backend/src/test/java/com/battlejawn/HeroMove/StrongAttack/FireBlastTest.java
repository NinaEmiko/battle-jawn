package com.battlejawn.HeroMove.StrongAttack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class FireBlastTest {
    @Test
    void fireBlastMissedTest() {
        FireBlast fireBlast = new FireBlast();
        FireBlast spyFireBlast = spy(fireBlast);
        when(spyFireBlast.miss()).thenReturn(true);
        when(spyFireBlast.criticalHit()).thenReturn(false);
        int damage = spyFireBlast.attack();
        Assertions.assertEquals(damage, spyFireBlast.getDamage());
    }
    @Test
    void fireBlastCriticalHitTest() {
        FireBlast fireBlast = new FireBlast();
        FireBlast spyFireBlast = spy(fireBlast);
        when(spyFireBlast.miss()).thenReturn(false);
        when(spyFireBlast.criticalHit()).thenReturn(true);
        int damage = spyFireBlast.attack();
        Assertions.assertEquals(damage, spyFireBlast.getDamage());
    }
    @Test
    void fireBlastCriticalHitFalseTest() {
        FireBlast fireBlast = new FireBlast();
        FireBlast spyFireBlast = spy(fireBlast);
        when(spyFireBlast.miss()).thenReturn(false);
        when(spyFireBlast.criticalHit()).thenReturn(false);
        int damage = spyFireBlast.attack();
        Assertions.assertEquals(damage, spyFireBlast.getDamage());
    }
}
