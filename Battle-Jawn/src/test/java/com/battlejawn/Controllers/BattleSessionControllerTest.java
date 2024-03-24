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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
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
//    @Test
//    void createNewBattleSessionTest() {
//        String requestBody = "{\"id\": 123}";
//
//        Long parsedHeroId = 123L;
//        when(jsonParser.extractHeroId(requestBody)).thenReturn(parsedHeroId);
//
//        BattleSession battleSession = new BattleSession();
//        when(battleSessionService.createNewBattleSession(parsedHeroId)).thenReturn(battleSession);
//
//        ResponseEntity<BattleSession> response = battleSessionController.createNewBattleSession(requestBody);
//
//        verify(jsonParser, times(1)).extractHeroId(requestBody);
//        verify(battleSessionService, times(1)).createNewBattleSession(parsedHeroId);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(battleSession, response.getBody());
//        assertEquals(URI.create("/session/" + battleSession.getId()), response.getHeaders().getLocation());
//
//    }
//    @Test
//    void createNewBattleSessionNullTest() {
//        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
//        when(battleSessionService.createNewBattleSession(anyLong())).thenReturn(null);
//        battleSessionController.createNewBattleSession(anyString());
//        verify(battleSessionService, times(1)).createNewBattleSession(anyLong());
//    }

}