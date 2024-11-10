package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.order.CustomOrder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomOrderRepository extends JpaRepository<CustomOrder, Long> {

}
