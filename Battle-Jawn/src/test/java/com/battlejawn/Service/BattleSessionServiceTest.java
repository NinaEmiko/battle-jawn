package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Enemy.Orc;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Repository.BattleSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleSessionServiceTest {

    @Mock
    BattleSessionRepository battleSessionRepository;
    @Mock
    EnemyService enemyService;
    @Mock
    BattleHistoryMessageService battleHistoryMessageService;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @Mock
    BattleSession battleSession;
    @Mock
    Enemy enemy;
    @Mock
    Hero hero;
    @Mock
    HeroService heroService;
    @InjectMocks
    BattleSessionService battleSessionService;
    @BeforeEach
    void setup() {

    }
    @Test
    void getBattleSessionByIdTest() {
        when(battleSessionRepository.findById(anyLong())).thenReturn(Optional.of(battleSession));
        battleSessionService.getBattleSessionById(anyLong());
        verify(battleSessionRepository, times(1)).findById(anyLong());
    }
    @Test
    void getBattleSessionByIdExceptionTest() {
        when(battleSessionRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> battleSessionService.getBattleSessionById(anyLong()));
    }

    @Test
    void createNewBattleSessionTest(){

    }

//    @Test
//    void createNewBattleSessionExceptionTest(){
//        when(enemyService.createNewEnemy(anyInt())).thenThrow(new RuntimeException());
//        assertThrows(RuntimeException.class, () -> battleSessionService.createNewBattleSession(anyLong()));
//    }

}