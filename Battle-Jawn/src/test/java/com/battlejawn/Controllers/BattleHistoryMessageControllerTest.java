//package com.battlejawn.Controllers;
//
//import com.battlejawn.Service.BattleHistoryMessageService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import java.util.Arrays;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(controllers = BattleHistoryMessageController.class)
//class BattleHistoryMessageControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @MockBean
//    private BattleHistoryMessageService battleHistoryMessageService;
//    @InjectMocks
//    private BattleHistoryMessageController battleHistoryMessageController;
//
//    @Test
//    public void testGetBattleHistoryMessagesByBattleSessionId() throws Exception {
//        List<String> mockBattleHistoryMessageList = Arrays.asList("Message 1", "Message 2");
//        when(battleHistoryMessageService.getBattleHistoryMessagesByBattleSessionId(anyLong())).thenReturn(mockBattleHistoryMessageList);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/battle-history-message/{id}", 123L))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        List<String> actualBattleHistoryMessageList = Arrays.asList(
//                objectMapper.readValue(result.getResponse().getContentAsString(), String[].class)
//        );
//        assertEquals(mockBattleHistoryMessageList, actualBattleHistoryMessageList);
//        verify(battleHistoryMessageService).getBattleHistoryMessagesByBattleSessionId(123L);
//    }
//
//}