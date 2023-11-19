package com.battlejawn.Service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.battlejawn.Repository.PlayerTipRepository;

@ExtendWith(MockitoExtension.class)
public class PlayerTipServiceTest {

    @Mock
    PlayerTipRepository playerTipRepository;

    // @Test
    // void testGetRandomPlayerTip() {
    //     //GIVEN

    //     //WHEN
    //         Mockito.when(playerTipRepository.findAll()).thenReturn(Mockito.any());
    //     //THEN
    //     verify();
    // }
}
