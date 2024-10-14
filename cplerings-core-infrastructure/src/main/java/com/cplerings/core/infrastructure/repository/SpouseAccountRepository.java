package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.spouse.SpouseAccount;

public interface SpouseAccountRepository extends JpaRepository<SpouseAccount, Long> {
}
