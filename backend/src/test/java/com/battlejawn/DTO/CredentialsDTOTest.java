package com.battlejawn.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CredentialsDTOTest {

    @Mock
    char[] charArray;
    @BeforeEach
    void setup(){
        charArray = "test".toCharArray();
    }
    @Test
    void allArgsConstructorTest(){
        CredentialsDTO credentialsDTO = new CredentialsDTO("", charArray);
        assertNotNull(credentialsDTO);
    }
    @Test
    void builderTest(){
        CredentialsDTO credentialsDTO = CredentialsDTO.builder()
                .login("")
                .password(charArray)
                .build();
        assertNotNull(credentialsDTO);
    }
}
