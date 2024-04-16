package com.battlejawn.Controllers;

import com.battlejawn.Service.BattleHistoryMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleHistoryMessageControllerTest {
    @Mock
    BattleHistoryMessageService battleHistoryMessageService;
    @Mock
    List<String> battleHistoryMessages;
    @InjectMocks
    private BattleHistoryMessageController battleHistoryMessageController;
    @BeforeEach
    void setup() {

    }
    @Test
    public void getBattleHistoryMessagesByBattleSessionIdTest() {
        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(battleHistoryMessages);
        battleHistoryMessageController.getBattleHistoryMessagesByBattleSessionId(anyLong());
        verify(battleHistoryMessageService, times(1)).getBattleHistoryMessagesByBattleSessionId(anyLong());
    }

}