package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.order.StandardOrderHistory;

public interface StandardOrderHistoryRepository extends JpaRepository<StandardOrderHistory, Long> {
}
