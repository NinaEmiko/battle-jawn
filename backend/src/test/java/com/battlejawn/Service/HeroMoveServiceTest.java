package com.battlejawn.Service;

import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.HeroMove.Attack.*;
import com.battlejawn.HeroMove.StrongAttack.*;
import com.battlejawn.HeroMove.Heal.*;
import com.battlejawn.HeroMove.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroMoveServiceTest {
    @Mock
    Stab stab;
    @Mock
    Strike strike;
    @Mock
    Holy holy;
    @Mock
    FireBlast fireBlast;
    @Mock
    IceBlast iceBlast;
    @Mock
    Impale impale;
    @Mock
    BackStab backStab;
    @Mock
    Wand wand;
    @Mock
    Run run;
    @Mock
    Block block;
    @Mock
    Steal steal;
    @Mock
    Potion potion;
    @Mock
    Water water;
    @Mock
    Heal heal;
    @InjectMocks
    HeroMoveService heroMoveService;
    @Mock
    HeroMoveDTO heroMoveDTO;

    @Test
    void heroMoveTest() {
        when(wand.attack(anyLong())).thenReturn(heroMoveDTO);
        when(stab.attack(anyLong())).thenReturn(heroMoveDTO);
        when(strike.attack(anyLong())).thenReturn(heroMoveDTO);
        when(fireBlast.attack(anyLong())).thenReturn(heroMoveDTO);
        when(iceBlast.attack(anyLong())).thenReturn(heroMoveDTO);
        when(holy.attack(anyLong())).thenReturn(heroMoveDTO);
        when(impale.attack(anyLong())).thenReturn(heroMoveDTO);
        when(backStab.attack(anyLong())).thenReturn(heroMoveDTO);
        when(heal.useHeal(anyLong())).thenReturn(heroMoveDTO);
        when(steal.processSteal(anyLong())).thenReturn(heroMoveDTO);
        when(block.processBlock(anyLong())).thenReturn(heroMoveDTO);
        when(potion.processPotion(anyLong())).thenReturn(heroMoveDTO);
        when(water.processWater(anyLong())).thenReturn(heroMoveDTO);
        when(run.processRun(anyLong())).thenReturn(heroMoveDTO);

        heroMoveService.heroMove("Wand", 1L);
        heroMoveService.heroMove("Strike", 1L);
        heroMoveService.heroMove("Stab", 1L);
        heroMoveService.heroMove("FireBlast", 1L);
        heroMoveService.heroMove("IceBlast", 1L);
        heroMoveService.heroMove("Holy", 1L);
        heroMoveService.heroMove("Impale", 1L);
        heroMoveService.heroMove("BackStab", 1L);
        heroMoveService.heroMove("Heal", 1L);
        heroMoveService.heroMove("Steal", 1L);
        heroMoveService.heroMove("Block", 1L);
        heroMoveService.heroMove("Potion", 1L);
        heroMoveService.heroMove("Water", 1L);
        heroMoveService.heroMove("Run", 1L);

        verify(wand, times(1)).attack(1L);
        verify(strike, times(1)).attack(1L);
        verify(stab, times(1)).attack(1L);
        verify(fireBlast, times(1)).attack(1L);
        verify(iceBlast, times(1)).attack(1L);
        verify(holy, times(1)).attack(1L);
        verify(impale, times(1)).attack(1L);
        verify(backStab, times(1)).attack(1L);
        verify(heal, times(1)).useHeal(1L);
        verify(steal, times(1)).processSteal(1L);
        verify(block, times(1)).processBlock(1L);
        verify(potion, times(1)).processPotion(1L);
        verify(water, times(1)).processWater(1L);
        verify(run, times(1)).processRun(1L);
    }


    @Test
    void exceptionTest() {
        when(wand.attack(anyLong())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> heroMoveService.heroMove("Wand", 1L));

    }

    @Test
    void nullTest() {
        assertNull(heroMoveService.heroMove("", 1L));
    }
}
