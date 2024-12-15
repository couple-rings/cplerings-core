package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.resell.ResellOrder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResellOrderRepository extends JpaRepository<ResellOrder, Long> {

}