package com.battlejawn.Service;

import com.battlejawn.Config.AppException;
import com.battlejawn.DTO.CredentialsDTO;
import com.battlejawn.Entities.UserAccount;
import com.battlejawn.Mapper.UserAccountMapper;
import com.battlejawn.Repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {
    @Mock
    UserAccountRepository userAccountRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserAccountMapper userAccountMapper;
    @Mock
    UserAccount userAccount;
    @Mock
    CredentialsDTO credentialsDTO;
    @InjectMocks
    UserAccountService userAccountService;
    @BeforeEach
    void setup(){
        char[] password = new char[5];
        credentialsDTO = new CredentialsDTO("login", password);
    }
    @Test
    void loginTest() {
        when(userAccountRepository.findByLogin(credentialsDTO.getLogin())).thenReturn(Optional.of(userAccount));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        userAccountService.login(credentialsDTO);
        verify(userAccountMapper, times(1)).toUserAccountDTO(any());
    }
    @Test
    void loginExceptionTest() {
        when(userAccountRepository.findByLogin(credentialsDTO.getLogin())).thenReturn(Optional.of(userAccount));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(userAccountMapper.toUserAccountDTO(userAccount)).thenThrow(new AppException("Invalid password", HttpStatus.BAD_REQUEST));
        assertThrows(AppException.class, () -> userAccountService.login(credentialsDTO));
    }
    @Test
    void deleteUserAccountByIdTest() {
        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.of(userAccount));
        doNothing().when(userAccountRepository).deleteById(anyLong());
        userAccountService.deleteUserAccountById(anyLong());
        verify(userAccountRepository, times(1)).deleteById(anyLong());
    }
    @Test
    void deleteUserAccountByIdNullTest() {
        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.empty());
        userAccountService.deleteUserAccountById(anyLong());
        verify(userAccountRepository, times(0)).deleteById(anyLong());
    }
//    @Test
//    void findByLoginTest() {
//        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.of(userAccount));
//        userAccountService.findByLogin(anyString());
//        verify(userAccountRepository, times(1)).findByLogin(anyString());
//    }

}