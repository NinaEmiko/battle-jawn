package com.battlejawn.HeroMove.StrongAttack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BlastTest {
    @Test
    void blastMissedTest() {
        Blast blast = new Blast();
        Blast spyBlast = spy(blast);
        when(spyBlast.miss()).thenReturn(true);
        when(spyBlast.criticalHit()).thenReturn(false);
        int damage = spyBlast.attack();
        Assertions.assertEquals(damage, spyBlast.getDamage());
    }
    @Test
    void blastCriticalHitTest() {
        Blast blast = new Blast();
        Blast spyBlast = spy(blast);
        when(spyBlast.miss()).thenReturn(false);
        when(spyBlast.criticalHit()).thenReturn(true);
        int damage = spyBlast.attack();
        Assertions.assertEquals(damage, spyBlast.getDamage());
    }
    @Test
    void blastCriticalHitFalseTest() {
        Blast blast = new Blast();
        Blast spyBlast = spy(blast);
        when(spyBlast.miss()).thenReturn(false);
        when(spyBlast.criticalHit()).thenReturn(false);
        int damage = spyBlast.attack();
        Assertions.assertEquals(damage, spyBlast.getDamage());
    }
}
