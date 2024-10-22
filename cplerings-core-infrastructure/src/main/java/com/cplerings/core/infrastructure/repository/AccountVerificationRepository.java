package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.account.AccountVerification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Long> {

    Optional<AccountVerification> findByAccountEmail(String email);
}