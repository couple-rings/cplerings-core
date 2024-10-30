package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.design.CustomDesign;

public interface CustomDesignRepository extends JpaRepository<CustomDesign, Long> {
}
