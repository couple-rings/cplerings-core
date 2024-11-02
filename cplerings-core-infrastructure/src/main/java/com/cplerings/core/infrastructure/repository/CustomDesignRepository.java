package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.design.CustomDesign;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomDesignRepository extends JpaRepository<CustomDesign, Long> {
}
