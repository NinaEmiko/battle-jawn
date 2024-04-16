package com.battlejawn.Controllers;

import com.battlejawn.Service.LootService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LootControllerTest {
    @Mock
    LootService lootService;
    @InjectMocks
    LootController lootController;
    @BeforeEach
    void setup() {

    }
    @Test
    void getLootTest(){
        List<String> jawn = new ArrayList<>();
        when(lootService.getLoot(anyLong())).thenReturn(jawn);
        lootController.getLoot(1L);
        verify(lootService, times(1)).getLoot(anyLong());
    }
}
