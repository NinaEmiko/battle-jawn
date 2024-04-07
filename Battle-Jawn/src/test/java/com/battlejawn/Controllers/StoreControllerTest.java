package com.battlejawn.Controllers;

import com.battlejawn.Config.JsonParser;
import com.battlejawn.Service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreControllerTest {
    @Mock
    StoreService storeService;
    @Mock
    JsonParser jsonParser;
    @InjectMocks
    StoreController storeController;
    @BeforeEach
    void setup(){

    }
    @Test
    void buyTest() {
        doNothing().when(storeService).buy(anyLong(), anyString(), anyInt());
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(jsonParser.extractItem(anyString())).thenReturn("");
        when(jsonParser.extractQuantity(anyString())).thenReturn(1);
        storeController.buy(anyString());
        verify(storeService, times(1)).buy(anyLong(), anyString(), anyInt());
    }
}

