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
        when(storeService.buy(anyLong(), anyString(), anyInt())).thenReturn("Hi");
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(jsonParser.extractItem(anyString())).thenReturn("");
        when(jsonParser.extractQuantity(anyString())).thenReturn(1);
        storeController.buy(anyString());
        verify(storeService, times(1)).buy(anyLong(), anyString(), anyInt());
    }
    @Test
    void buyNullTest() {
        when(storeService.buy(anyLong(), anyString(), anyInt())).thenReturn(null);
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(jsonParser.extractItem(anyString())).thenReturn("");
        when(jsonParser.extractQuantity(anyString())).thenReturn(1);
        storeController.buy(anyString());
        verify(storeService, times(1)).buy(anyLong(), anyString(), anyInt());
    }

    @Test
    void sellTest() {
        when(storeService.sell(anyLong(), anyString(), anyInt())).thenReturn("Hi");
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(jsonParser.extractItem(anyString())).thenReturn("");
        when(jsonParser.extractQuantity(anyString())).thenReturn(1);
        storeController.sell(anyString());
        verify(storeService, times(1)).sell(anyLong(), anyString(), anyInt());
    }
    @Test
    void sellNullTest() {
        when(storeService.sell(anyLong(), anyString(), anyInt())).thenReturn(null);
        when(jsonParser.extractHeroId(anyString())).thenReturn(1L);
        when(jsonParser.extractItem(anyString())).thenReturn("");
        when(jsonParser.extractQuantity(anyString())).thenReturn(1);
        storeController.sell(anyString());
        verify(storeService, times(1)).sell(anyLong(), anyString(), anyInt());
    }
}

