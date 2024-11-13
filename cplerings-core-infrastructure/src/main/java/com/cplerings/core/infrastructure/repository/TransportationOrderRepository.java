package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.order.TransportationOrder;

public interface TransportationOrderRepository extends JpaRepository<TransportationOrder, Long> {
}
