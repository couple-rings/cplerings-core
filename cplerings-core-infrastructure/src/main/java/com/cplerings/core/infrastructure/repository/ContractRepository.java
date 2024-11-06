package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.contract.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
