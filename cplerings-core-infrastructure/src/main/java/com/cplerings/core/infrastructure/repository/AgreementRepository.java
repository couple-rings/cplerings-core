package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.spouse.Agreement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

    boolean existsByCustomerId(Long customerId);
}