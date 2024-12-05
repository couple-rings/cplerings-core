package com.cplerings.core.infrastructure.datasource.payment;

import java.util.Optional;

import com.cplerings.core.application.payment.datasource.GetPaymentDataSource;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.QPayment;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedPaymentDataSource extends AbstractDataSource implements GetPaymentDataSource {

    private static final QPayment Q_PAYMENT = QPayment.payment;

    @Override
    public Optional<Payment> getPaymentById(Long paymentId) {
        return Optional.ofNullable(createQuery()
                .select(Q_PAYMENT)
                .from(Q_PAYMENT)
                .where(Q_PAYMENT.id.eq(paymentId))
                .fetchFirst());
    }
}
