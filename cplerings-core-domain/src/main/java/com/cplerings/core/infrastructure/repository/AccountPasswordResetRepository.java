package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.account.AccountPasswordReset;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountPasswordResetRepository extends JpaRepository<AccountPasswordReset, Long> {

}