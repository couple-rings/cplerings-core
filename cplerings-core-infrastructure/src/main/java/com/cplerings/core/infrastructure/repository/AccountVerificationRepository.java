package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.account.AccountVerification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountVerificationRepository extends JpaRepository<AccountVerification, Long> {

    @Modifying
    @Query(
            value = """
                    UPDATE AccountVerification SET state = 'INACTIVE'
                    WHERE account.id = ?1
                    """
    )
    void disableVerificationCodes(Long id);
}