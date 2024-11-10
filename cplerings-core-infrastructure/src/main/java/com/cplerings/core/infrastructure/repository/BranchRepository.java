package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.branch.Branch;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}