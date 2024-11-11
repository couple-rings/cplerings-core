package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.diamond.DiamondSpecification;

public interface DiamondSpecificationRepository extends JpaRepository<DiamondSpecification, Long> {
}
