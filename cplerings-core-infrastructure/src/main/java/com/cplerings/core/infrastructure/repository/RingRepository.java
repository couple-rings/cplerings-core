package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.ring.Ring;

public interface RingRepository extends JpaRepository<Ring, Long> {
}
