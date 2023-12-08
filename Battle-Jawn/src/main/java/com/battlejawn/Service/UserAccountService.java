package com.battlejawn.Service;

import com.battlejawn.Config.AppException;
import com.battlejawn.Controllers.HeroController;
import com.battlejawn.DTO.CredentialsDTO;
import com.battlejawn.DTO.SignUpDTO;
import com.battlejawn.Mapper.UserAccountMapper;
import com.battlejawn.DTO.UserAccountDTO;
import com.battlejawn.Entities.UserAccount;
import com.battlejawn.Repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.CharBuffer;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAccountMapper userAccountMapper;
    private final Logger logger = Logger.getLogger(HeroController.class.getName());

    public UserAccountService(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, UserAccountMapper userAccountMapper) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAccountMapper = userAccountMapper;
    }

    public UserAccountDTO login(CredentialsDTO credentialsDTO) {
        UserAccount userAccount = userAccountRepository.findByLogin(credentialsDTO.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.getPassword()), userAccount.getPassword())) {
            return userAccountMapper.toUserAccountDTO(userAccount);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public UserAccountDTO register(SignUpDTO signUpDTO) {
        logger.info("Inside register service method. Sign Up DTO: " + signUpDTO);
        Optional<UserAccount> optionalUser = userAccountRepository.findByLogin(signUpDTO.getLogin());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        UserAccount userAccount = userAccountMapper.signUpToUserAccount(signUpDTO);
        userAccount.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.getPassword())));

        UserAccount savedUserAccount = userAccountRepository.save(userAccount);

        return userAccountMapper.toUserAccountDTO(savedUserAccount);
    }

    public UserAccountDTO findByLogin(String login) {
        UserAccount userAccount = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userAccountMapper.toUserAccountDTO(userAccount);
    }

    public UserAccountDTO getUserAccountById(Long id) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userAccountMapper.toUserAccountDTO(userAccount);
    }

}