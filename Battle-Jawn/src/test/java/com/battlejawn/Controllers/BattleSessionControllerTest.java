package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Service.BattleSessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleSessionControllerTest {
    @Mock
    BattleSessionService battleSessionService;
    @Mock
    BattleSession battleSession;
    @Mock
    JsonParser jsonParser;
    @InjectMocks
    BattleSessionController battleSessionController;
    @BeforeEach
    void setup() {

    }
    @Test
    void getBattleSessionByIdTest() {
        battleSession.setId(1L);
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(battleSession);
        battleSessionController.getBattleSessionById(anyLong());
        verify(battleSessionService, times(1)).getBattleSessionById(anyLong());
    }
    @Test
    void getBattleSessionByIdNullTest() {
        when(battleSessionService.getBattleSessionById(anyLong())).thenReturn(null);
        battleSessionController.getBattleSessionById(anyLong());
        verify(battleSessionService, times(1)).getBattleSessionById(anyLong());
    }
    @Test
    void createNewBattleSessionTest() {
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(battleSessionService.createNewBattleSession(anyLong())).thenReturn(battleSession);
        battleSessionController.createNewBattleSession(anyString());
        verify(battleSessionService, times(1)).createNewBattleSession(anyLong());
    }
    @Test
    void createNewBattleSessionNullTest() {
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(battleSessionService.createNewBattleSession(anyLong())).thenReturn(null);
        battleSessionController.createNewBattleSession(anyString());
        verify(battleSessionService, times(1)).createNewBattleSession(anyLong());
    }

    @Test
    void processEndOfBattleTest() {
        when(jsonParser.extractBattleSessionId(anyString())).thenReturn(1L);
        when(jsonParser.extractBattleResult(anyString())).thenReturn("String");
        when(battleSessionService.processEndOfBattle(anyLong(), anyString())).thenReturn("Another string");
        battleSessionController.processEndOfBattle(anyString());
        verify(battleSessionService, times(1)).processEndOfBattle(anyLong(), anyString());
    }
    @Test
    void processEndOfBattleNullTest() {
        when(jsonParser.extractBattleSessionId(anyString())).thenReturn(1L);
        when(jsonParser.extractBattleResult(anyString())).thenReturn("String");
        when(battleSessionService.processEndOfBattle(anyLong(), anyString())).thenReturn(null);
        battleSessionController.processEndOfBattle(anyString());
        verify(battleSessionService, times(1)).processEndOfBattle(anyLong(), anyString());
    }
}