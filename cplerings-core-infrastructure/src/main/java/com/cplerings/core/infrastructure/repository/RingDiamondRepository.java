package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.ring.RingDiamond;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RingDiamondRepository extends JpaRepository<RingDiamond, Long> {
}