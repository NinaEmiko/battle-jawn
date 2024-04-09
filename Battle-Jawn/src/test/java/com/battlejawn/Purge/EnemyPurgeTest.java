package com.battlejawn.Purge;

import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Wolf;
import com.battlejawn.Repository.EnemyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnemyPurgeTest {
    @Mock
    EnemyRepository enemyRepository;
    @Mock
    List<Enemy> enemies;
    @Mock
    Enemy enemy;
    @InjectMocks
    EnemyPurge enemyPurge;
    @BeforeEach
    void setup() {
        enemies = new ArrayList<>();
        enemy = new Wolf(2, 55, 12);
        enemy.setId(1L);
        enemy.setCreatedAt(LocalDateTime.now().minusDays(6));
        enemies.add(enemy);
    }
    @Test
    void purgeEnemyTest() {
        when(enemyRepository.findAll()).thenReturn(enemies);
        doNothing().when(enemyRepository).deleteById(anyLong());
        enemyPurge.purgeEnemy();
        verify(enemyRepository, times(1)).deleteById(anyLong());
    }
    @Test
    void purgeEnemyExceptionTest() {
        when(enemyRepository.findAll()).thenReturn(enemies);
        doThrow(new RuntimeException()).when(enemyRepository).deleteById(anyLong());
        assertThrows(RuntimeException.class, () -> enemyPurge.purgeEnemy());
    }

}