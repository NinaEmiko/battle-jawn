package com.battlejawn.Controllers;

import com.battlejawn.Config.UserAuthenticationProvider;
import com.battlejawn.DTO.SignUpDTO;
import com.battlejawn.DTO.UpdatePasswordDTO;
import com.battlejawn.DTO.UserAccountDTO;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Entities.UserAccount;
import com.battlejawn.Service.UserAccountService;
import com.battlejawn.DTO.CredentialsDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final List<UserAccount> userAccounts = new ArrayList<>();
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final Logger logger = Logger.getLogger(UserAccountController.class.getName());

    @PostMapping("/login")
    public ResponseEntity<UserAccountDTO> login(@RequestBody @Valid CredentialsDTO credentialsDTO) {
        UserAccountDTO userAccountDTO = userAccountService.login(credentialsDTO);
        userAccountDTO.setToken(userAuthenticationProvider.createToken(userAccountDTO.getLogin()));
        return ResponseEntity.ok(userAccountDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccountDTO> register(@RequestBody @Valid SignUpDTO signUpDTO) {
        logger.info("Inside register controller method. Sign Up DTO: " + signUpDTO);
        UserAccountDTO userAccountDTO = userAccountService.register(signUpDTO);
        logger.info("User registered: " + userAccountDTO.getLogin());
        userAccountDTO.setToken(userAuthenticationProvider.createToken(signUpDTO.getLogin()));
        logger.info("Token created: " + userAccountDTO.getToken());
        return ResponseEntity.created(URI.create("/user-account/" + userAccountDTO.getId())).body(userAccountDTO);
    }

    @GetMapping("/api/user-account/{id}")
    public ResponseEntity<UserAccountDTO> getUserAccountById(@PathVariable("id") Long id) {
        logger.info("Inside getUserAccountById controller method. User Account ID: " + id + ".");
        UserAccountDTO userAccountDTO = userAccountService.getUserAccountById(id);

        if (userAccountDTO != null) {
            return new ResponseEntity<>(userAccountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        logger.info("Inside deleteAccountById controller method. User Account Id: " + id);
        String response = userAccountService.deleteUserAccountById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePasswordByUserAccountId(@PathVariable Long id, @RequestBody @Valid UpdatePasswordDTO updatePasswordDTO) {
        logger.info("Inside updatePasswordByAccountById controller method. User Account Id: " + id + ". New Password: " + updatePasswordDTO + ".");
        String response = userAccountService.updatePasswordByUserAccountId(id, updatePasswordDTO);
        return ResponseEntity.ok(response);
    }
}