package com.battlejawn.Scheduler;

import com.battlejawn.Service.HeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroHealthReplenishSchedulerTest {
    @Mock
    HeroService heroService;
    @InjectMocks
    HeroHealthReplenishScheduler heroHealthReplenishScheduler;
    @BeforeEach
    void setup(){

    }
    @Test
    void replenishAllHeroesHealthTest(){
        doNothing().when(heroService).restAllHeroes();
        heroHealthReplenishScheduler.replenishAllHeroesHealth();
        verify(heroService, times(1)).restAllHeroes();
    }
    @Test
    void replenishAllHeroesHealthExceptionTest(){
        doThrow(new RuntimeException()).when(heroService).restAllHeroes();
        assertThrows(RuntimeException.class, () -> heroHealthReplenishScheduler.replenishAllHeroesHealth());
    }

}
