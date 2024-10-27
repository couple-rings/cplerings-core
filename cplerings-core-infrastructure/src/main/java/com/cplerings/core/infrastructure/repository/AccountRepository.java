package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<Account> findByEmailAndStatus(String email, AccountStatus status);
}
