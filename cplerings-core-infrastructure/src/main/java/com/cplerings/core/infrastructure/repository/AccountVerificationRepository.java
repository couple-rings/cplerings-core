package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.account.AccountVerification;

public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Long> {

}