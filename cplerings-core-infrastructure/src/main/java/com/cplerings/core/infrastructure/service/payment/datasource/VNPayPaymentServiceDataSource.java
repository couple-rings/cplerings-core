package com.cplerings.core.infrastructure.service.payment.datasource;

import com.cplerings.core.domain.payment.Payment;

public interface VNPayPaymentServiceDataSource {

    Payment save(Payment payment);

    boolean paymentExistsAndIsPending(Long paymentId);
}
