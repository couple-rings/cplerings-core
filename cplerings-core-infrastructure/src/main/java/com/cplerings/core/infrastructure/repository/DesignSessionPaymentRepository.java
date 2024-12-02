package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.payment.DesignSessionPayment;

public interface DesignSessionPaymentRepository extends JpaRepository<DesignSessionPayment, Long> {

}