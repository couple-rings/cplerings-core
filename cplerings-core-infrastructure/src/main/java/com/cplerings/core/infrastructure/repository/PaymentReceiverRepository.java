package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.payment.PaymentReceiver;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentReceiverRepository extends JpaRepository<PaymentReceiver, Long> {

    Optional<PaymentReceiver> findByReceiverId(String receiverId);

    Optional<PaymentReceiver> findByPaymentId(Long paymentId);
}