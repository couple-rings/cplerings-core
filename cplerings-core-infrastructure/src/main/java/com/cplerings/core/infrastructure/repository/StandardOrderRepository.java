package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.order.StandardOrder;

public interface StandardOrderRepository extends JpaRepository<StandardOrder, Long> {
}
