package com.battlejawn.Controllers;

import com.battlejawn.Entities.PlayerTip;
import com.battlejawn.Service.PlayerTipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerTipControllerTest {
    @Mock
    PlayerTipService playerTipService;
    @Mock
    List<PlayerTip> playerTips;
    @Mock
    PlayerTip playerTip;
    @InjectMocks
    PlayerTipController playerTipController;
    @BeforeEach
    void setup(){

    }
    @Test
    void getAllPlayerTipTest() {
        when(playerTipService.getAllPlayerTips()).thenReturn(playerTips);
        playerTipController.getAllPlayerTip();
        verify(playerTipService, times(1)).getAllPlayerTips();
    }
    @Test
    void getPlayerTipByIdTest() {
        when(playerTipService.getPlayerTipById(anyLong())).thenReturn(playerTip);
        playerTipController.getPlayerTipById(anyLong());
        verify(playerTipService, times(1)).getPlayerTipById(anyLong());
    }
    @Test
    void getRandomTipTest() {
        when(playerTipService.getRandomPlayerTip()).thenReturn("This");
        playerTipController.getRandomTip();
        verify(playerTipService, times(1)).getRandomPlayerTip();
    }
    @Test
    void createNewPlayerTipTest() {
        when(playerTipService.savePlayerTip(any())).thenReturn(playerTip);
        playerTipController.createNewPlayerTip(anyString());
        verify(playerTipService, times(1)).savePlayerTip(any());
    }
    @Test
    void updatePlayerTipTest() {
        doNothing().when(playerTipService).updatePlayerTip(anyLong(), anyString());
        playerTipController.updatePlayerTip(anyLong(), anyString());
        verify(playerTipService, times(1)).updatePlayerTip(anyLong(), anyString());
    }
    @Test
    void deletePlayerTipByIdTest() {
        when(playerTipService.deletePlayerTip(anyLong())).thenReturn("This");
        playerTipController.deletePlayerTipById(anyLong());
        verify(playerTipService, times(1)).deletePlayerTip(anyLong());
    }
}