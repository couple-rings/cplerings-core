package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.payment.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}