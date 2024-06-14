package com.battlejawn.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdatePasswordDTOTest {
    @Mock
    char[] charArray;
    @BeforeEach
    void setup(){
        charArray = "test".toCharArray();
    }
    @Test
    void allArgsConstructorTest(){
        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO(charArray, charArray);
        assertNotNull(updatePasswordDTO);
    }
    @Test
    void builderTest(){
        UpdatePasswordDTO updatePasswordDTO = UpdatePasswordDTO.builder()
                .newPassword(charArray)
                .build();
        assertNotNull(updatePasswordDTO);
    }
}
