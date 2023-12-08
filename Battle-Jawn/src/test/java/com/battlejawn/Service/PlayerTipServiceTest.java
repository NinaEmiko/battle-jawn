package com.battlejawn.Service;

import com.battlejawn.Entities.PlayerTip;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.battlejawn.Repository.PlayerTipRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerTipServiceTest {

    @Mock
    PlayerTipRepository playerTipRepository;

    @InjectMocks
    private PlayerTipService playerTipService;

    @Test
    void testGetPlayerTipById() {
        long playerTipId = 123;
        PlayerTip expectedPlayerTip = new PlayerTip();
        expectedPlayerTip.setId(playerTipId);

        when(playerTipRepository.findById(playerTipId)).thenReturn(Optional.of(expectedPlayerTip));

        PlayerTip actualPlayerTip = playerTipService.getPlayerTipById(playerTipId);

        assertEquals(expectedPlayerTip, actualPlayerTip);

        verify(playerTipRepository).findById(playerTipId);
    }

//     @Test
//     void testGetRandomPlayerTip() {
//
//     }


}
