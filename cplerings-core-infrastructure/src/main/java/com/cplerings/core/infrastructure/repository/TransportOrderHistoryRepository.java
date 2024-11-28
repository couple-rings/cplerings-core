package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.order.TransportOrderHistory;

public interface TransportOrderHistoryRepository extends JpaRepository<TransportOrderHistory, Long> {
}
