package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.*;
import com.battlejawn.Randomizer.Randomizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LootServiceTest {
    @Mock
    Randomizer randomizer;
    @InjectMocks
    LootService lootService;
    @Mock
    EnemyService enemyService;
    @BeforeEach
    void setup() {

    }
    @Test
    void getWolfLootTest() {
        Enemy wolf = new Wolf(1, 50, 10);
        wolf.setId(1L);
        when(enemyService.getEnemyById(any())).thenReturn(wolf);
        when(randomizer.getRandomInt(anyInt())).thenReturn(10).thenReturn(10);
        List<String> loot = lootService.getLoot(1L);
        Assertions.assertNotNull(loot);
    }
    @Test
    void getSpiritLootTest() {
        Enemy spirit = new Spirit(1, 50, 10);
        spirit.setId(1L);
        when(enemyService.getEnemyById(any())).thenReturn(spirit);
        List<String> loot = lootService.getLoot(1L);
        Assertions.assertNotNull(loot);
    }
    @Test
    void getOrcLootTest() {
        Enemy orc = new Orc(1, 1, 3, 1);
        orc.setId(1L);
        when(enemyService.getEnemyById(any())).thenReturn(orc);
        when(randomizer.getRandomInt(anyInt())).thenReturn(10).thenReturn(10).thenReturn(10).thenReturn(10).thenReturn(10);
        List<String> loot = lootService.getLoot(1L);
        Assertions.assertNotNull(loot);
    }
    @Test
    void getThiefLootTest() {
        Enemy thief = new Thief(1, 1, 3, 3, 1);
        thief.setId(1L);
        when(enemyService.getEnemyById(any())).thenReturn(thief);
        when(randomizer.getRandomInt(anyInt())).thenReturn(10).thenReturn(10).thenReturn(10).thenReturn(10).thenReturn(10);
        List<String> loot = lootService.getLoot(1L);
        Assertions.assertNotNull(loot);
    }
    @Test
    void getLootNullTest() {
        Enemy nullEnemy = new Orc(1, 1, 1, 1);
        nullEnemy.setName("Null");
        nullEnemy.setId(1L);
        when(enemyService.getEnemyById(any())).thenReturn(nullEnemy);
        List<String> loot = lootService.getLoot(1L);
        Assertions.assertNull(loot);
    }
}
