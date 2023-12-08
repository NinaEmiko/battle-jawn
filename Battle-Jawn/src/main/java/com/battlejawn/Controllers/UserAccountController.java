package com.battlejawn.Controllers;

import com.battlejawn.Config.UserAuthenticationProvider;
import com.battlejawn.DTO.SignUpDTO;
import com.battlejawn.DTO.UserAccountDTO;
import com.battlejawn.Service.UserAccountService;
import com.battlejawn.DTO.CredentialsDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
public class UserAccountController {

    private final UserAccountService userAccountService;
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

}