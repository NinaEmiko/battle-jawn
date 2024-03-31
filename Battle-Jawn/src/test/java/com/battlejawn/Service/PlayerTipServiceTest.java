package com.battlejawn.Service;

import com.battlejawn.Config.AppException;
import com.battlejawn.Entities.PlayerTip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.battlejawn.Repository.PlayerTipRepository;
import org.springframework.http.HttpStatus;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerTipServiceTest {

    @Mock
    PlayerTipRepository playerTipRepository;
    @Mock
    List<PlayerTip> playerTips;
    @Mock
    PlayerTip playerTip;
    @InjectMocks
    PlayerTipService playerTipService;
    @BeforeEach
    void setup(){
        playerTip = new PlayerTip();
        playerTips = new ArrayList<>();
    }

    @Test
    void getAllPlayerTipsTest() {
        when(playerTipRepository.findAll()).thenReturn(playerTips);
        playerTipService.getAllPlayerTips();
        verify(playerTipRepository, times(1)).findAll();
    }

    @Test
    void testGetPlayerTipByIdTest() {
        when(playerTipRepository.findById(anyLong())).thenReturn(Optional.ofNullable(playerTip));
        playerTipService.getPlayerTipById(anyLong());
        verify(playerTipRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetPlayerTipByIdExceptionTest() {
        when(playerTipRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> playerTipService.getPlayerTipById(anyLong()));
    }

     @Test
     void testGetRandomPlayerTip() {
        playerTip.setBody("Some body");
        playerTips.add(playerTip);
        when(playerTipRepository.findAll()).thenReturn(playerTips);
        String playerTipBody = playerTipService.getRandomPlayerTip();
        assertNotNull(playerTipBody);
     }

    @Test
    void savePlayerTipTest(){
        when(playerTipRepository.save(playerTip)).thenReturn(playerTip);
        playerTipService.savePlayerTip(playerTip);

        verify(playerTipRepository).save(playerTip);
    }

    @Test
    void deletePlayerTipTest() {
        doNothing().when(playerTipRepository).deleteById(anyLong());
        playerTipService.deletePlayerTip(anyLong());
        verify(playerTipRepository, times(1)).deleteById(anyLong());
    }
}
