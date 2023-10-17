package com.battlejawn.Battle.Jawn;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.battlejawn.Repository.PlayerTipRepository;
import com.battlejawn.Service.PlayerTipService;

@ExtendWith(MockitoExtension.class)
public class PlayerTipTest {

    @Mock
    PlayerTipRepository playerTipRepository;

    @Mock
    PlayerTipService playerTipService;
    
    // @Test
    // public void testGetRandomPlayerTip() {
    //     List<PlayerTip> tips = new ArrayList<>();
    //     tips.add(new PlayerTip((long) 1, "Quote 1"));
    //     Mockito.when(playerTipRepository.findAll()).thenReturn(tips);

    // }
}
