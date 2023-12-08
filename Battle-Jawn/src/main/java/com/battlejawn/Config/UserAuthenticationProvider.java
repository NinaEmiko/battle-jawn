package com.battlejawn.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.battlejawn.Controllers.UserAccountController;
import com.battlejawn.DTO.UserAccountDTO;
import com.battlejawn.Service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class UserAuthenticationProvider {

    private final String secretKey;
    private final UserAccountService userAccountService;
    private final Logger logger = Logger.getLogger(UserAuthenticationProvider.class.getName());
    @Autowired
    public UserAuthenticationProvider(@Value("${security.jwt.token.secret-key:secret-key}") String secretKey,
                                      UserAccountService userAccountService) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.userAccountService = userAccountService;
    }

    public String createToken(String login) {
        logger.info("Creating token for login: " + login);
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserAccountDTO userAccount = userAccountService.findByLogin(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(userAccount, null, Collections.emptyList());
    }

}