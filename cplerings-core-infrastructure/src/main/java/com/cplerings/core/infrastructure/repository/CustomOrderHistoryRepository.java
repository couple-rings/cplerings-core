package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.order.CustomOrderHistory;

public interface CustomOrderHistoryRepository extends JpaRepository<CustomOrderHistory, Long> {
}
