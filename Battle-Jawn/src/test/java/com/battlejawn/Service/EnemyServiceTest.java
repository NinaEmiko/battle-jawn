package com.battlejawn.Service;

import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Orc;
import com.battlejawn.Repository.EnemyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnemyServiceTest {

    @Mock
    private EnemyRepository enemyRepository;

    @InjectMocks
    private EnemyService enemyService;

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

}