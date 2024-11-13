package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.order.TransportationOrder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationOrderRepository extends JpaRepository<TransportationOrder, Long> {

    boolean existsByCustomOrderId(Long customOrderId);
}