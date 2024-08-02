package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Entities.Hero.Tank;
import com.battlejawn.Service.TalentTreeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TalentTreeControllerTest {
    @Mock
    TalentTreeService talentTreeService;
    @Mock
    JsonParser jsonParser;
    @InjectMocks
    TalentTreeController talentTreeController;
    @Test
    void activateTalentTest(){
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(jsonParser.extractTalent(anyString())).thenReturn("");
        when(talentTreeService.activateTalent(anyLong(), anyString())).thenReturn("");

        talentTreeController.activateTalent("");

        verify(talentTreeService, times(1)).activateTalent(1L,"");
    }
    @Test
    void resetTalentsTest(){
        Tank tank = new Tank("Tank");
        tank.setId(1L);

        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(talentTreeService.resetTalents(anyLong())).thenReturn(tank);

        talentTreeController.resetTalents("");

        verify(talentTreeService, times(1)).resetTalents(1L);
    }
    @Test
    void activateTalentNullTest(){
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(jsonParser.extractTalent(anyString())).thenReturn("");
        when(talentTreeService.activateTalent(anyLong(), anyString())).thenReturn(null);

        talentTreeController.activateTalent("");

        verify(talentTreeService, times(1)).activateTalent(1L,"");
    }
    @Test
    void resetTalentsNullTest(){
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(talentTreeService.resetTalents(anyLong())).thenReturn(null);

        talentTreeController.resetTalents("");

        verify(talentTreeService, times(1)).resetTalents(1L);
    }

}
