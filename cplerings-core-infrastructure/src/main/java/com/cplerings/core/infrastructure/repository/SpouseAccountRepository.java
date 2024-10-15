package com.cplerings.core.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cplerings.core.domain.spouse.SpouseAccount;

public interface SpouseAccountRepository extends JpaRepository<SpouseAccount, Long> {

    @Query(
            value = """
                        select sa from SpouseAccount sa
                        where sa.spouse.id = ?1
                        and sa.customer.id = ?2
                    """)
    Optional<SpouseAccount> findBySpouseAndCustomer(Long spouseId, Long customerId);
}
