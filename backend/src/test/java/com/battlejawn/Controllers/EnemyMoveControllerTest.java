package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.DTO.EnemyMoveDTO;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Service.EnemyMoveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnemyMoveControllerTest {
    @Mock
    EnemyMoveService enemyMoveService;
    @Mock
    JsonParser jsonParser;
    @Mock
    EnemyMoveDTO enemyMoveDTO;
    @InjectMocks
    EnemyMoveController enemyMoveController;
    @BeforeEach
    void setup(){
        enemyMoveDTO = new EnemyMoveDTO();

    }
    @Test
    void enemyMoveTest(){
        when(jsonParser.extractBattleSessionId(anyString())).thenReturn(1L);
        when(enemyMoveService.enemyMove(anyLong())).thenReturn(enemyMoveDTO);
        enemyMoveController.enemyMove(anyString());
        verify(enemyMoveService, times(1)).enemyMove(anyLong());
    }
    @Test
    void enemyMoveNullTest(){
        when(jsonParser.extractBattleSessionId(anyString())).thenReturn(1L);
        when(enemyMoveService.enemyMove(anyLong())).thenReturn(null);
        enemyMoveController.enemyMove(anyString());
        verify(enemyMoveService, times(1)).enemyMove(anyLong());
    }

}