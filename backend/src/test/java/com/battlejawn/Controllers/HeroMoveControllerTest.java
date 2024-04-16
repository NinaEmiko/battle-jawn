package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.DTO.HeroMoveDTO;
import com.battlejawn.Service.HeroMoveService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeroMoveControllerTest {
    @Mock
    HeroMoveService heroMoveService;
    @Mock
    HeroMoveDTO heroMoveDTO;
    @Mock
    JsonParser jsonParser;
    @InjectMocks
    HeroMoveController heroMoveController;
    @BeforeEach
    void setup() {
    }
    @Test
    void heroMoveTest() {
        when(jsonParser.extractMove(anyString())).thenReturn("This");
        when(jsonParser.extractBattleSessionId(anyString())).thenReturn(1L);
        when(heroMoveService.heroMove(anyString(), anyLong())).thenReturn(heroMoveDTO);
        heroMoveController.heroMove(anyString());
        verify(heroMoveService, times(1)).heroMove(anyString(), anyLong());
    }
    @Test
    void heroMoveNullTest() {
        when(jsonParser.extractMove(anyString())).thenReturn("This");
        when(jsonParser.extractBattleSessionId(anyString())).thenReturn(1L);
        when(heroMoveService.heroMove(anyString(), anyLong())).thenReturn(null);
        heroMoveController.heroMove(anyString());
        verify(heroMoveService, times(1)).heroMove(anyString(), anyLong());
    }

}