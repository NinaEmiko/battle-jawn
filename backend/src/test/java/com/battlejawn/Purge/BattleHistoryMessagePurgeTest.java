package com.battlejawn.Purge;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Repository.BattleHistoryMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleHistoryMessagePurgeTest {
    @Mock
    BattleHistoryMessageRepository battleHistoryMessageRepository;
    @Mock
    List<BattleHistoryMessage> battleHistoryMessages;
    @InjectMocks
    BattleHistoryMessagePurge battleHistoryMessagePurge;
    @BeforeEach
    void setup(){
        battleHistoryMessages = new ArrayList<>();
        BattleHistoryMessage battleHistoryMessage = new BattleHistoryMessage(2L, "That", LocalDateTime.now().minusDays(5));
        battleHistoryMessage.setBattleSessionId(1L);
        battleHistoryMessage.setCreatedAt(LocalDateTime.now().minusDays(6));
        battleHistoryMessage.setMessage("This");
        battleHistoryMessage.setBattleSessionId(2L);
        battleHistoryMessages.add(battleHistoryMessage);
    }
    @Test
    void purgeBattleHistoryMessagesTest() {
        doNothing().when(battleHistoryMessageRepository).deleteById(any());
        when(battleHistoryMessageRepository.findAll()).thenReturn(battleHistoryMessages);
        battleHistoryMessagePurge.purgeBattleHistoryMessage();
        verify(battleHistoryMessageRepository, times(1)).deleteById(any());
    }

    @Test
    void purgeBattleHistoryMessagesExceptionTest() {
        when(battleHistoryMessageRepository.findAll()).thenReturn(battleHistoryMessages);
        doThrow(new RuntimeException()).when(battleHistoryMessageRepository).deleteById(anyLong());
        assertThrows(RuntimeException.class, () -> battleHistoryMessagePurge.purgeBattleHistoryMessage());
    }

}