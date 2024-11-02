package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.design.Design;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository extends JpaRepository<Design, Long> {
}
