package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.ring.RingHistory;

public interface RingHistoryRepository extends JpaRepository<RingHistory, Long> {
}
