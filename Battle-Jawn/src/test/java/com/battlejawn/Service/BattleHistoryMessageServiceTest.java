package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Repository.BattleHistoryMessageRepository;
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
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BattleHistoryMessageServiceTest {
    @Mock
    private BattleHistoryMessageRepository battleHistoryMessageRepository;

    @InjectMocks
    private BattleHistoryMessageService battleHistoryMessageService;

//    @Test
//    void testCreateNewMessage() {
//        Long battleSessionId = 123L;
//        String message = "New Message";
//        LocalDateTime now = LocalDateTime.now();
//        BattleHistoryMessage expectedBattleHistoryMessage = new BattleHistoryMessage();
//        expectedBattleHistoryMessage.setMessage(message);
//        expectedBattleHistoryMessage.setCreatedAt(now);
//        expectedBattleHistoryMessage.setBattleSessionId(battleSessionId);
//        when(battleHistoryMessageRepository.save(any(BattleHistoryMessage.class))).thenReturn(expectedBattleHistoryMessage);
//        BattleHistoryMessage createdBattleHistoryMessage = battleHistoryMessageService.createNewMessage(battleSessionId, message);
//        verify(battleHistoryMessageRepository).save(argThat(battleHistoryMessage ->
//                battleHistoryMessage.getMessage().equals(message) &&
//                        battleHistoryMessage.getCreatedAt().isEqual(now) &&
//                        battleHistoryMessage.getBattleSessionId().equals(battleHistoryMessage)
//        ));
//        assertNotNull(createdBattleHistoryMessage);
//        assertEquals(expectedBattleHistoryMessage, createdBattleHistoryMessage);
//    }

    @Test
    void testGetBattleHistoryMessagesByBattleSessionId() {
        Long battleSessionId = 123L;
        List<String> mockBattleHistoryMessages = Arrays.asList("Message 1", "Message 2");
        when(battleHistoryMessageRepository.findMessagesByBattleSessionId(battleSessionId)).thenReturn(mockBattleHistoryMessages);
        List<String> actualBattleHistoryMessages = battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(battleSessionId);
        verify(battleHistoryMessageRepository).findMessagesByBattleSessionId(battleSessionId);
        assertEquals(mockBattleHistoryMessages, actualBattleHistoryMessages);
    }
}