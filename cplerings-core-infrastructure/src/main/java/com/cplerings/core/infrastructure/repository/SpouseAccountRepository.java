package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.spouse.SpouseAccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpouseAccountRepository extends JpaRepository<SpouseAccount, Long> {

    Optional<SpouseAccount> findSpouseAccountBySpouseCitizenId(String spouseCitizenId);
}
