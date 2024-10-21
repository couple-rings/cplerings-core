package com.cplerings.core.application.payment.datasource;

import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;

import java.util.Optional;

public interface ProcessVNPayPaymentDataSource {

    Optional<Payment> findPaymentByIdWithVNPayTransaction(Long paymentId);

    Payment save(Payment payment);

    void save(VNPayTransaction vnPayTransaction);
}
