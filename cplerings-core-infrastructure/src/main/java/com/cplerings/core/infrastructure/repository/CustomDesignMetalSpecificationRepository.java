package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.design.CustomDesignMetalSpecification;

public interface CustomDesignMetalSpecificationRepository extends JpaRepository<CustomDesignMetalSpecification, Long> {
}
