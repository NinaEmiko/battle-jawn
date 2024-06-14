package com.battlejawn.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ErrorDTOTest {
    @Test
    void allArgsConstructorTest(){
        ErrorDTO errorDTO = new ErrorDTO("", "");
        errorDTO.setException("test");
        errorDTO.setMessage("test");
        assertEquals(errorDTO.getException(), "test");
        assertEquals(errorDTO.getMessage(), "test");
    }
    @Test
    void dataTest(){
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message("test")
                .exception("test")
                .build();

        assertNotNull(errorDTO);
    }
}
