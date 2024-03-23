package com.battlejawn.Controllers;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Service.BattleHistoryMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
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