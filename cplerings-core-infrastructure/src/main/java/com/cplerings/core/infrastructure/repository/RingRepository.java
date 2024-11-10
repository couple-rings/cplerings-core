package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.ring.Ring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RingRepository extends JpaRepository<Ring, Long> {
}
