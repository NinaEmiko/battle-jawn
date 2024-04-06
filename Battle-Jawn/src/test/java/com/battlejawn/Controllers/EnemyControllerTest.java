package com.battlejawn.Controllers;

import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Service.EnemyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnemyControllerTest {
    @Mock
    EnemyService enemyService;
    @Mock
    List<Enemy> enemies;
    @Mock
    Enemy enemy;
    @InjectMocks
    EnemyController enemyController;
    @BeforeEach
    void setup() {

    }
    @Test
    void getAllEnemiesTest() {
        when(enemyService.getAllEnemies()).thenReturn(enemies);
        enemyController.getAllEnemies();
        verify(enemyService, times(1)).getAllEnemies();
    }
    @Test
    void getEnemyByIdTest() {
        when(enemyService.getEnemyById(anyLong())).thenReturn(enemy);
        enemyController.getEnemyById(anyLong());
        verify(enemyService, times(1)).getEnemyById(anyLong());
    }
    @Test
    void getEnemyByIdNullTest() {
        when(enemyService.getEnemyById(anyLong())).thenReturn(null);
        enemyController.getEnemyById(anyLong());
        verify(enemyService, times(1)).getEnemyById(anyLong());
    }
    @Test
    void getHealthByIdTest() {
        when(enemyService.getEnemyHealthById(anyLong())).thenReturn(1);
        enemyController.getHealthById(anyLong());
        verify(enemyService, times(1)).getEnemyHealthById(anyLong());
    }
    @Test
    void getHealthByIdNullTest() {
        when(enemyService.getEnemyHealthById(anyLong())).thenReturn(null);
        enemyController.getHealthById(anyLong());
        verify(enemyService, times(1)).getEnemyHealthById(anyLong());
    }
    @Test
    void createNewEnemyTest() {
        when(enemyService.createNewEnemy(anyInt())).thenReturn(enemy);
        enemyController.createNewEnemy(anyInt());
        verify(enemyService, times(1)).createNewEnemy(anyInt());
    }
    @Test
    void createNewEnemyNullTest() {
        when(enemyService.createNewEnemy(anyInt())).thenReturn(null);
        enemyController.createNewEnemy(anyInt());
        verify(enemyService, times(1)).createNewEnemy(anyInt());
    }
}