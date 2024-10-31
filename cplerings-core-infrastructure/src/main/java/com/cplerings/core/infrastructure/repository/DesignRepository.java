package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.design.Design;

public interface DesignRepository extends JpaRepository<Design, Long> {
}
