package com.cplerings.core.application.payment.datasource;

import java.util.Optional;

import com.cplerings.core.domain.payment.Payment;

public interface GetPaymentDataSource {

    Optional<Payment> getPaymentById(Long paymentId);
}
