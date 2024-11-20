package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.diamond.Diamond;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiamondRepository extends JpaRepository<Diamond, Long> {
}