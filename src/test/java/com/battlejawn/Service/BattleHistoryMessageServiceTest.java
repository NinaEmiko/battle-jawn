package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Repository.BattleHistoryMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleHistoryMessageServiceTest {
    @Mock
    BattleHistoryMessageRepository battleHistoryMessageRepository;
    @Mock
    BattleHistoryMessage battleHistoryMessage;
    @InjectMocks
    BattleHistoryMessageService battleHistoryMessageService;
    @BeforeEach
    void setup() {
        battleHistoryMessage = new BattleHistoryMessage();
    }

    @Test
    void createNewMessageTest(){
        when(battleHistoryMessageRepository.save(any())).thenReturn(battleHistoryMessage);
        battleHistoryMessageService.createNewMessage(1L, "1L");
        verify(battleHistoryMessageRepository, times(1)).save(any());
    }

    @Test
    void testGetBattleHistoryMessagesByBattleSessionId() {
        when(battleHistoryMessageRepository.findMessagesByBattleSessionId(anyLong())).thenReturn(anyList());
        battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(1L);
        verify(battleHistoryMessageRepository, times(1)).findMessagesByBattleSessionId(anyLong());
    }
}