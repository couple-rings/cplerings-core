package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.metal.MetalSpecification;

public interface MetalSpecificationRepository extends JpaRepository<MetalSpecification, Long> {
}
