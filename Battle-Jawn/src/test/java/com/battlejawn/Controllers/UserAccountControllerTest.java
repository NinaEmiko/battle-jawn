package com.battlejawn.Controllers;

import com.battlejawn.Config.UserAuthenticationProvider;
import com.battlejawn.DTO.CredentialsDTO;
import com.battlejawn.DTO.SignUpDTO;
import com.battlejawn.DTO.UserAccountDTO;
import com.battlejawn.Entities.UserAccount;
import com.battlejawn.Service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountControllerTest {
    @Mock
    UserAccountService userAccountService;
    @Mock
    List<UserAccount> userAccounts;
    @Mock
    UserAuthenticationProvider userAuthenticationProvider;
    @Mock
    CredentialsDTO credentialsDTO;
    @Mock
    SignUpDTO signUpDTO;
    @Mock
    UserAccountDTO userAccountDTO;
    @InjectMocks
    UserAccountController userAccountController;
    @BeforeEach
    void setup(){

    }
    @Test
    void loginTest() {
        when(userAccountService.login(any())).thenReturn(userAccountDTO);
        when(userAuthenticationProvider.createToken(any())).thenReturn("This");
        userAccountController.login(any());
        verify(userAccountService, times(1)).login(any());
        verify(userAuthenticationProvider, times(1)).createToken(any());
    }
    @Test
    void register() {
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setLogin("This");

        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setId(1L);

        when(userAccountService.register(signUpDTO)).thenReturn(userAccountDTO);
        when(userAuthenticationProvider.createToken(signUpDTO.getLogin())).thenReturn("That");

        ResponseEntity<UserAccountDTO> response = userAccountController.register(signUpDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userAccountDTO, response.getBody());
        assertEquals(URI.create("/user-account/" + userAccountDTO.getId()), response.getHeaders().getLocation());
    }
    @Test
    void getUserAccountByIdTest() {
        when(userAccountService.getUserAccountById(anyLong())).thenReturn(userAccountDTO);
        userAccountController.getUserAccountById(anyLong());
        verify(userAccountService, times(1)).getUserAccountById(anyLong());
    }
    @Test
    void getUserAccountByIdNullTest() {
        when(userAccountService.getUserAccountById(anyLong())).thenReturn(null);
        userAccountController.getUserAccountById(anyLong());
        verify(userAccountService, times(1)).getUserAccountById(anyLong());
    }
    @Test
    void deleteAccountByIdTest() {
        when(userAccountService.deleteUserAccountById(anyLong())).thenReturn("This");
        userAccountController.deleteAccountById(anyLong());
        verify(userAccountService, times(1)).deleteUserAccountById(anyLong());
    }
    @Test
    void updatePasswordByUserAccountIdTest() {
        when(userAccountService.updatePasswordByUserAccountId(anyLong(), any())).thenReturn("This");
        userAccountController.updatePasswordByUserAccountId(anyLong(), any());
        verify(userAccountService, times(1)).updatePasswordByUserAccountId(anyLong(), any());
    }
}