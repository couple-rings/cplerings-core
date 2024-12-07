package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.refund.Refund;

public interface RefundRepository extends JpaRepository<Refund, Long> {
}
