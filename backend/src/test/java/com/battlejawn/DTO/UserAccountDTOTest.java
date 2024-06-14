package com.battlejawn.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserAccountDTOTest {
    @Test
    void allArgsConstructorTest(){
        UserAccountDTO userAccountDTO = new UserAccountDTO(1L,"","");
        assertNotNull(userAccountDTO);
    }
    @Test
    void builderTest(){
        UserAccountDTO userAccountDTO = UserAccountDTO.builder()
                .id(1L)
                .login("")
                .token("")
                .build();
        assertNotNull(userAccountDTO);
    }
}
