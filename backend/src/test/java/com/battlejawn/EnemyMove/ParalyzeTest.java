//package com.battlejawn.EnemyMove;
//
//import com.battlejawn.HeroMove.Run;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.core.parameters.P;
//
//import static org.mockito.Mockito.spy;
//import static org.mockito.Mockito.when;
//
//public class ParalyzeTest {
//    @Test
//    void useParalyzeTrueTest() {
//        Paralyze paralyze = new Paralyze();
//        Paralyze spyParalyze = spy(paralyze);
//        when(spyParalyze.useParalyze()).thenReturn(true);
//        boolean paralyzeBool = spyParalyze.useParalyze();
//        Assertions.assertTrue(paralyzeBool);
//    }
//    @Test
//    void useParalyzeFalseTest() {
//        Paralyze paralyze = new Paralyze();
//        Paralyze spyParalyze = spy(paralyze);
//        when(spyParalyze.useParalyze()).thenReturn(false);
//        boolean paralyzeBool = spyParalyze.useParalyze();
//        Assertions.assertFalse(paralyzeBool);
//    }
//}
