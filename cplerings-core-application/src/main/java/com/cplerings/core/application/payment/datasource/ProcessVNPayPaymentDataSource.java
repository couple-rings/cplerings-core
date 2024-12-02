package com.cplerings.core.application.payment.datasource;

import java.util.Optional;

import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;

public interface ProcessVNPayPaymentDataSource {

    Optional<Payment> findPaymentByIdWithVNPayTransaction(Long paymentId);

    Payment save(Payment payment);

    void save(VNPayTransaction vnPayTransaction);
}
