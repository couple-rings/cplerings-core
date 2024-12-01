package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.order.StandardOrderItem;

public interface StandardOrderItemRepository extends JpaRepository<StandardOrderItem, Long> {
}
