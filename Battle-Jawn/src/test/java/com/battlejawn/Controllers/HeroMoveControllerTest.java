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
//    @Test
//    void heroMoveTest() {
//
//        String data = "{\"thing\":\"parsedThing\",\"id\":123}";
//
//        when(jsonParser.extractMove(data)).thenReturn("parsedThing");
//        when(jsonParser.extractBattleSessionId(data)).thenReturn(123L);
//
//        when(heroMoveService.heroMove("parsedThing", 123L)).thenReturn(heroMoveDTO);
//
//        ResponseEntity<HeroMoveDTO> responseEntity = heroMoveController.heroMove(data);
//
//        verify(jsonParser).extractMove(data);
//        verify(jsonParser).extractBattleSessionId(data);
//        verify(heroMoveService).heroMove("parsedThing", 123L);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals(heroMoveDTO, responseEntity.getBody());
//
//        URI expectedLocation = URI.create("/thing-dto/");
//        assertEquals(expectedLocation, responseEntity.getHeaders().getLocation());
//    }

}