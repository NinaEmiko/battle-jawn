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
    //OLD TEST
    @Test
    void testGetEnemyById(){
        long enemyId = 1L;
        Orc orc = new Orc();
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
        doNothing().when(enemyRepository).updateHealthById(anyInt(), anyLong());
        enemyService.updateHealthById(anyInt(), anyLong());
        verify(enemyRepository, times(1)).updateHealthById(anyInt(), anyLong());
    }
    @Test
    void updatePotionCountByIdTest() {
        doNothing().when(enemyRepository).updatePotionCountById(anyInt(), anyLong());
        enemyService.updatePotionCountById(anyInt(), anyLong());
        verify(enemyRepository, times(1)).updatePotionCountById(anyInt(), anyLong());
    }
    @Test
    void createNewEnemyTest() {
        when(randomizer.getRandomInt(anyInt())).thenReturn(1);
        Enemy orc = enemyService.createNewEnemy();
        verify(randomizer, times(1)).getRandomInt(anyInt());
        when(randomizer.getRandomInt(anyInt())).thenReturn(2);
        Enemy spirit = enemyService.createNewEnemy();
        verify(randomizer, times(2)).getRandomInt(anyInt());
        when(randomizer.getRandomInt(anyInt())).thenReturn(3);
        Enemy thief = enemyService.createNewEnemy();
        verify(randomizer, times(3)).getRandomInt(anyInt());
        when(randomizer.getRandomInt(anyInt())).thenReturn(4);
        Enemy wolf = enemyService.createNewEnemy();
        verify(randomizer, times(4)).getRandomInt(anyInt());

        Assertions.assertEquals("Thief", thief.getName());
        Assertions.assertEquals("Wolf", wolf.getName());
        Assertions.assertEquals("Spirit", spirit.getName());
        Assertions.assertEquals("Orc", orc.getName());

    }
    @Test
    void createNewEnemyExceptionTest() {
        when(randomizer.getRandomInt(anyInt())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> enemyService.createNewEnemy());

    }
}