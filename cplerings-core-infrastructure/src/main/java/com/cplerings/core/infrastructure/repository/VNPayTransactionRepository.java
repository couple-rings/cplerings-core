package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.payment.transaction.VNPayTransaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VNPayTransactionRepository extends JpaRepository<VNPayTransaction, Long> {

}