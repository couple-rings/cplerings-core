package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.order.CustomOrder;

public interface CustomOrderRepository extends JpaRepository<CustomOrder, Integer> {
}
