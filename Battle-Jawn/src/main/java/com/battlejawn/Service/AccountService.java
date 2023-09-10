package com.battlejawn.Service;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.battlejawn.Entities.Account;
import com.battlejawn.Repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.getById(id);
    }

    @Transactional
    public Account saveAccount(String username){
        try {
            Account account = new Account();
            account.setUsername(username);
            account.setCreationDate(LocalDateTime.now());
            accountRepository.save(account);
            return account;
        } catch(Exception e) {
            throw new RuntimeException("Failed to save the account: " + e.getMessage());
        }
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }
    
}
