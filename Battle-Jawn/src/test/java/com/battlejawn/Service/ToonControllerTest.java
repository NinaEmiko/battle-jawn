package com.battlejawn.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ToonControllerTest {

    @Mock
    private ToonService toonService;

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testGetById() {
        

        // Mockito.when(ToonService.getToonById(Mockito.anyLong())).thenReturn();
    }

    
}
