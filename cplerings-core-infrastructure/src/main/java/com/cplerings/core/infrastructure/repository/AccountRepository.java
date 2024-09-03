package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.account.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
