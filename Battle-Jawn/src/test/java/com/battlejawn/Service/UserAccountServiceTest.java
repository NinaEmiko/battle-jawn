package com.battlejawn.Service;

import com.battlejawn.Config.AppException;
import com.battlejawn.DTO.CredentialsDTO;
import com.battlejawn.DTO.SignUpDTO;
import com.battlejawn.DTO.UpdatePasswordDTO;
import com.battlejawn.DTO.UserAccountDTO;
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

import java.nio.CharBuffer;
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
    UserAccount existingUserAccount;
    @Mock
    CredentialsDTO validCredentials;
    @Mock
    CredentialsDTO invalidCredentials;
    @Mock
    UserAccount validUserAccount;
    @Mock
    SignUpDTO signUpDTO;
    @InjectMocks
    UserAccountService userAccountService;
    @BeforeEach
    void setup(){
        signUpDTO = new SignUpDTO();

        validCredentials = new CredentialsDTO();
        validCredentials.setLogin("validUser");
        validCredentials.setPassword("validPassword".toCharArray());

        invalidCredentials = new CredentialsDTO();
        invalidCredentials.setLogin("invalidUser");
        invalidCredentials.setPassword("invalidPassword".toCharArray());

        validUserAccount = new UserAccount();
        validUserAccount.setLogin("validUser");
        validUserAccount.setPassword("hashedValidPassword");
    }
    @Test
    void testLoginValidUser() {
        when(userAccountRepository.findByLogin(validCredentials.getLogin())).thenReturn(Optional.of(validUserAccount));
        when(passwordEncoder.matches(any(CharBuffer.class), any(String.class))).thenReturn(true);
        when(userAccountMapper.toUserAccountDTO(validUserAccount)).thenReturn(new UserAccountDTO());

        UserAccountDTO result = userAccountService.login(validCredentials);

        assertEquals(UserAccountDTO.class, result.getClass());
    }

    @Test
    void testLoginInvalidPassword() {
        when(userAccountRepository.findByLogin(validCredentials.getLogin())).thenReturn(Optional.of(validUserAccount));
        when(passwordEncoder.matches(any(CharBuffer.class), any(String.class))).thenReturn(false);

        assertThrows(AppException.class, () -> userAccountService.login(validCredentials));
    }

    @Test
    void testLoginUserNotFound() {
        when(userAccountRepository.findByLogin(invalidCredentials.getLogin())).thenReturn(Optional.empty());

        assertThrows(AppException.class, () -> userAccountService.login(invalidCredentials));
    }

    @Test
    void testRegisterNewUser() {
        signUpDTO.setLogin("newUser");
        signUpDTO.setPassword("password123".toCharArray());

        userAccount.setLogin(signUpDTO.getLogin());
        userAccount.setPassword("encodedPassword");

        when(userAccountRepository.findByLogin(signUpDTO.getLogin())).thenReturn(Optional.empty());
        when(userAccountMapper.signUpToUserAccount(signUpDTO)).thenReturn(userAccount);
        when(passwordEncoder.encode(any(CharBuffer.class))).thenReturn("encodedPassword");
        when(userAccountRepository.save(userAccount)).thenReturn(userAccount);
        when(userAccountMapper.toUserAccountDTO(userAccount)).thenReturn(new UserAccountDTO());

        UserAccountDTO result = userAccountService.register(signUpDTO);

        assertEquals(UserAccountDTO.class, result.getClass());
    }

    @Test
    void testRegisterExistingUser() {
        signUpDTO.setLogin("existingUser");
        signUpDTO.setPassword("password123".toCharArray());

        existingUserAccount.setLogin(signUpDTO.getLogin());

        when(userAccountRepository.findByLogin(signUpDTO.getLogin())).thenReturn(Optional.of(existingUserAccount));

        assertThrows(AppException.class, () -> userAccountService.register(signUpDTO));
        verify(userAccountRepository, never()).save(any());
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
    @Test
    void testFindByLoginUserFound() {
        // Arrange
        String login = "existingUser";
        UserAccount existingUserAccount = new UserAccount();
        existingUserAccount.setLogin(login);

        when(userAccountRepository.findByLogin(login)).thenReturn(Optional.of(existingUserAccount));
        when(userAccountMapper.toUserAccountDTO(existingUserAccount)).thenReturn(new UserAccountDTO());

        // Act
        UserAccountDTO result = userAccountService.findByLogin(login);

        // Assert
        assertEquals(UserAccountDTO.class, result.getClass());
    }

    @Test
    void testFindByLoginUserNotFound() {
        // Arrange
        String login = "nonExistingUser";
        when(userAccountRepository.findByLogin(login)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(AppException.class, () -> userAccountService.findByLogin(login));
    }

    @Test
    void testGetUserAccountByIdUserFound() {
        // Arrange
        Long id = 1L;
        UserAccount existingUserAccount = new UserAccount();
        existingUserAccount.setId(id);

        when(userAccountRepository.findById(id)).thenReturn(Optional.of(existingUserAccount));
        when(userAccountMapper.toUserAccountDTO(existingUserAccount)).thenReturn(new UserAccountDTO());

        // Act
        UserAccountDTO result = userAccountService.getUserAccountById(id);

        // Assert
        assertEquals(UserAccountDTO.class, result.getClass());
    }

    @Test
    void testGetUserAccountByIdUserNotFound() {
        // Arrange
        Long id = 1L;
        when(userAccountRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(AppException.class, () -> userAccountService.getUserAccountById(id));
    }

//    @Test
//    void testUpdatePasswordByUserAccountIdUserFound() {
//        Long id = 1L;
//        String login = "login";
//        String existingPassword = "existingPassword";
//        String newPassword = "newPassword";
//        String encodedPassword = "encodedPassword";
//
//        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO();
//        updatePasswordDTO.setNewPassword(newPassword.toCharArray());
//
//        UserAccount userAccount = new UserAccount();
//        userAccount.setId(id);
//        userAccount.setLogin(login);
//        userAccount.setPassword(existingPassword);
//
//        when(userAccountRepository.findById(id)).thenReturn(Optional.of(userAccount));
//        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn(encodedPassword);
//
//        String result = userAccountService.updatePasswordByUserAccountId(id, updatePasswordDTO);
//
//        verify(userAccountRepository).findById(id);
//        verify(userAccountRepository).save(userAccount);
//        verify(passwordEncoder).encode(any(CharSequence.class));
//        assertEquals("Password updated successfully for user account ID: " + id + ".", result);
//        assertEquals(encodedPassword, userAccount.getPassword());
//    }

    @Test
    void testUpdatePasswordByUserAccountIdUserNotFound() {
        Long id = 1L;
        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO();
        updatePasswordDTO.setNewPassword("newPassword123".toCharArray());

        when(userAccountRepository.findById(id)).thenReturn(Optional.empty());

        String result = userAccountService.updatePasswordByUserAccountId(id, updatePasswordDTO);

        assertEquals("User Account with ID: " + id + " not found.", result);
        verify(userAccountRepository, never()).save(any());
    }

}