package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.contract.Contract;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

}
