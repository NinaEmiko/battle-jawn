package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Orc;
import com.battlejawn.Entities.Enemy.Thief;
import com.battlejawn.Entities.Enemy.Wolf;
import com.battlejawn.Randomizer.Randomizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoinProcessorServiceTest {
    @Mock
    Randomizer randomizer;
    @Mock
    Enemy enemy;
    @InjectMocks
    CoinProcessorService coinProcessorService;
    @BeforeEach
    void setup(){
    }
    @Test
    void processCoinsThiefTest() {
        enemy = new Thief(1, 90, 2, 4, 17);
        enemy.setName("Thief");

        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            when(coinProcessorService.processCoins(enemy)).thenReturn(1L);
            coinProcessorService.processCoins(enemy);
            verify(randomizer, times(i)).getRandomLong(anyLong());
        }
    }

    @Test
    void processCoinsOrcTest() {

        enemy = new Orc(1, 100, 3, 20);
        enemy.setName("Orc");

        for (int i = 1; i <= 10; i++) {
            enemy.setLevel(i);
            when(coinProcessorService.processCoins(enemy)).thenReturn(1L);
            coinProcessorService.processCoins(enemy);
            verify(randomizer, times(i)).getRandomLong(anyLong());
        }
    }
    @Test
    void processCoinsWolfTest() {

        enemy = new Wolf(1, 50, 10);
        enemy.setName("Wolf");

        coinProcessorService.processCoins(enemy);
        verify(randomizer, times(0)).getRandomLong(anyLong());
    }
}
