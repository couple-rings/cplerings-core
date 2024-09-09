package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.account.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
