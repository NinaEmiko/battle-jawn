package com.battlejawn.HeroMove;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class RunTest {
    @Test
    void useRunTrueTest() {
        Run run = new Run();
        Run spyRun = spy(run);
        when(spyRun.useRun()).thenReturn(true);
        boolean runBool = spyRun.useRun();
        Assertions.assertTrue(runBool);
    }
    @Test
    void useRunFalseTest() {
        Run run = new Run();
        Run spyRun = spy(run);
        when(spyRun.useRun()).thenReturn(false);
        boolean runBool = spyRun.useRun();
        Assertions.assertFalse(runBool);
    }
}
