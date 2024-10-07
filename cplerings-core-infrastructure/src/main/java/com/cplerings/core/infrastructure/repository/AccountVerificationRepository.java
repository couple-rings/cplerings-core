package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.account.AccountVerification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Long> {

}