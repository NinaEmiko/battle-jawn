package com.battlejawn.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SignUpDTOTest {
    @Mock
    char[] charArray;
    @BeforeEach
    void setup(){
        charArray = "test".toCharArray();
    }
    @Test
    void allArgsConstructorTest(){
        SignUpDTO signUpDTO = new SignUpDTO("", charArray);
        assertNotNull(signUpDTO);
    }
    @Test
    void builderTest(){
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .login("")
                .password(charArray)
                .build();
        assertNotNull(signUpDTO);
    }
}
