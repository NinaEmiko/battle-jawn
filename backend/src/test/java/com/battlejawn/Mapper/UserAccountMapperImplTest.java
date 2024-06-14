package com.battlejawn.Mapper;

import com.battlejawn.DTO.SignUpDTO;
import com.battlejawn.DTO.UserAccountDTO;
import com.battlejawn.Entities.UserAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserAccountMapperImplTest {
    @Test
    void toUserAccountDTOTest(){
        UserAccountMapperImpl userAccountMapper = new UserAccountMapperImpl();
        UserAccount userAccount = new UserAccount();
        UserAccountDTO userAccountDTO = userAccountMapper.toUserAccountDTO(userAccount);
        assertNotNull(userAccountDTO);
    }
    @Test
    void toUserAccountDTONullTest(){
        UserAccountMapperImpl userAccountMapper = new UserAccountMapperImpl();
        UserAccountDTO userAccountDTO = userAccountMapper.toUserAccountDTO(null);
        assertNull(userAccountDTO);
    }
    @Test
    void toSignUpDTOTest(){
        UserAccountMapperImpl userAccountMapper = new UserAccountMapperImpl();
        SignUpDTO signUpDTO = new SignUpDTO();
        UserAccount userAccount = userAccountMapper.signUpToUserAccount(signUpDTO);
        assertNotNull(userAccount);
    }
    @Test
    void toSignUpDTONullTest(){
        UserAccountMapperImpl userAccountMapper = new UserAccountMapperImpl();
        UserAccount userAccount = userAccountMapper.signUpToUserAccount(null);
        assertNull(userAccount);
    }
}
