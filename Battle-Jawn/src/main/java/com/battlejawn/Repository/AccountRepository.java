package com.battlejawn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.battlejawn.Entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
