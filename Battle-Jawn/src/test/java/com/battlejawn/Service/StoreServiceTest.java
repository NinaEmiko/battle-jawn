package com.battlejawn.Service;

import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.Hero.Tank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
    @Mock
    HeroService heroService;
    @Mock
    Hero hero;
    @InjectMocks
    StoreService storeService;
    @BeforeEach
    void setup(){
        hero = new Tank("Name");
    }
    @Test
    void buyTest() {
        when(heroService.getHeroById(anyLong())).thenReturn(hero);
        doNothing().when(heroService).updateHero(any());
        storeService.buy(1L, "potion", 1);
        verify(heroService, times(1)).updateHero(any());
    }
}
