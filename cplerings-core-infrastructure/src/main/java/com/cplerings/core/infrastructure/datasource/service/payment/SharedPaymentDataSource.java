package com.cplerings.core.infrastructure.datasource.service.payment;

import java.util.Optional;

import com.cplerings.core.application.payment.datasource.ProcessVNPayPaymentDataSource;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.QPayment;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.PaymentRepository;
import com.cplerings.core.infrastructure.repository.VNPayTransactionRepository;
import com.cplerings.core.infrastructure.service.payment.datasource.VNPayPaymentServiceDataSource;
import com.querydsl.core.types.dsl.Expressions;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedPaymentDataSource extends AbstractDataSource
        implements VNPayPaymentServiceDataSource, ProcessVNPayPaymentDataSource {

    private static final QPayment Q_PAYMENT = QPayment.payment;

    private final PaymentRepository paymentRepository;
    private final VNPayTransactionRepository vnPayTransactionRepository;

    @Override
    public Payment save(Payment payment) {
        updateAuditor(payment);
        return paymentRepository.save(payment);
    }

    @Override
    public boolean paymentExistsAndIsPending(Long paymentId) {
        return (createQuery().select(Expressions.ONE)
                .from(Q_PAYMENT)
                .where(Q_PAYMENT.id.eq(paymentId)
                        .and(Q_PAYMENT.status.eq(PaymentStatus.PENDING)))
                .fetchFirst() != null);
    }

    @Override
    public Optional<Payment> findPaymentByIdWithVNPayTransaction(Long paymentId) {
        return Optional.ofNullable(createQuery().select(Q_PAYMENT)
                .from(Q_PAYMENT)
                .leftJoin(Q_PAYMENT.vnPayTransaction)
                .leftJoin(Q_PAYMENT.customRequest)
                .leftJoin(Q_PAYMENT.designSessionPayment)
                .leftJoin(Q_PAYMENT.craftingStage)
                .where(Q_PAYMENT.id.eq(paymentId))
                .fetchFirst());
    }

    @Override
    public void save(VNPayTransaction vnPayTransaction) {
        updateAuditor(vnPayTransaction);
        vnPayTransactionRepository.save(vnPayTransaction);
    }
}
