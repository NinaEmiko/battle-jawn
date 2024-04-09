package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.*;
import com.battlejawn.Randomizer.Randomizer;
import com.battlejawn.Repository.EnemyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnemyServiceTest {

    @Mock
    private EnemyRepository enemyRepository;
    @Mock
    List<Enemy> enemies;
    @Mock
    Randomizer randomizer;
    @Mock
    Enemy enemy;
    @InjectMocks
    private EnemyService enemyService;

    @Test
    void getAllEnemiesTest(){
        when(enemyRepository.findAll()).thenReturn(enemies);
        enemyService.getAllEnemies();
        verify(enemyRepository, times(1)).findAll();
    }

    @Test
    void GetEnemyByIdTest(){
        long enemyId = 1L;
        Orc orc = new Orc(2, 105, 3, 25);
        orc.setId(enemyId);

        when(enemyRepository.findById(enemyId)).thenReturn(Optional.of(orc));

        Enemy actual = enemyService.getEnemyById(enemyId);

        assertEquals(actual.getId(), enemyId);
        verify(enemyRepository).findById(enemyId);
    }

    @Test
    void getEnemyByIdExceptionTest(){
        when(enemyRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> enemyService.getEnemyById(anyLong()));
    }

    @Test
    void getEnemyHealthByIdTest() {
        when(enemyRepository.findById(anyLong())).thenReturn(Optional.of(enemy));
        enemyService.getEnemyHealthById(anyLong());
        verify(enemyRepository, times(1)).findById(anyLong());
    }
    @Test
    void getEnemyHealthByIdExceptionTest(){
        when(enemyRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> enemyService.getEnemyHealthById(anyLong()));
    }
    @Test
    void updateHealthByIdTest() {
        when(enemyRepository.findById(anyLong())).thenReturn(Optional.of(enemy));
        when(enemyRepository.save(enemy)).thenReturn(enemy);
        enemyService.updateHealthById(1, 1L);
        verify(enemyRepository, times(1)).save(any());
    }
    @Test
    void updatePotionCountByIdTest() {
        when(enemyRepository.findById(anyLong())).thenReturn(Optional.of(enemy));
        when(enemyRepository.save(enemy)).thenReturn(enemy);
        enemyService.updatePotionCountById(1, 1L);
        verify(enemyRepository, times(1)).save(any());
    }
    @Test
    void createNewEnemyTest() {
        when(randomizer.getRandomInt(anyInt())).thenReturn(1);
        Enemy orc = enemyService.createNewEnemy(2);
        verify(randomizer, times(2)).getRandomInt(anyInt());
        when(randomizer.getRandomInt(anyInt())).thenReturn(2);
        Enemy spirit = enemyService.createNewEnemy(2);
        verify(randomizer, times(4)).getRandomInt(anyInt());
        when(randomizer.getRandomInt(anyInt())).thenReturn(3);
        Enemy thief = enemyService.createNewEnemy(2);
        verify(randomizer, times(6)).getRandomInt(anyInt());
        when(randomizer.getRandomInt(anyInt())).thenReturn(4);
        Enemy wolf = enemyService.createNewEnemy(2);
        verify(randomizer, times(8)).getRandomInt(anyInt());

        Assertions.assertEquals("Thief", thief.getName());
        Assertions.assertEquals("Wolf", wolf.getName());
        Assertions.assertEquals("Spirit", spirit.getName());
        Assertions.assertEquals("Orc", orc.getName());

    }
    @Test
    void createNewEnemyExceptionTest() {
        when(randomizer.getRandomInt(anyInt())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> enemyService.createNewEnemy(2));

    }
    @Test
    void createNewEnemyNullTest() {
        when(randomizer.getRandomInt(anyInt())).thenReturn(0);
        Enemy nullEnemy = enemyService.createNewEnemy(2);
        assertNull(nullEnemy);
    }

    @Test
    void createNewOrcTest() {
        for (int i = 1; i <= 10; i++) {
            when(randomizer.getRandomInt(anyInt())).thenReturn(1).thenReturn(i);
            when(enemyRepository.save(any())).thenReturn(enemy);
            enemyService.createNewEnemy(i);
            verify(enemyRepository, times(i)).save(any());
        }
    }
    @Test
    void createNewThiefTest() {
        for (int i = 1; i <= 10; i++) {
            when(randomizer.getRandomInt(anyInt())).thenReturn(3).thenReturn(i);
            when(enemyRepository.save(any())).thenReturn(enemy);
            enemyService.createNewEnemy(i);
            verify(enemyRepository, times(i)).save(any());
        }
    }
    @Test
    void createNewWolfTest() {
        for (int i = 1; i <= 10; i++) {
            when(randomizer.getRandomInt(anyInt())).thenReturn(4).thenReturn(i);
            when(enemyRepository.save(any())).thenReturn(enemy);
            enemyService.createNewEnemy(i);
            verify(enemyRepository, times(i)).save(any());
        }
    }
    @Test
    void createNewSpiritTest() {
        for (int i = 1; i <= 10; i++) {
            when(randomizer.getRandomInt(anyInt())).thenReturn(2).thenReturn(i);
            when(enemyRepository.save(any())).thenReturn(enemy);
            enemyService.createNewEnemy(i);
            verify(enemyRepository, times(i)).save(any());
        }
    }
}