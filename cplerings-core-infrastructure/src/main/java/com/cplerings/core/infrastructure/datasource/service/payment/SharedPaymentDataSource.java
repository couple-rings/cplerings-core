package com.cplerings.core.infrastructure.datasource.service.payment;

import com.cplerings.core.application.payment.datasource.ProcessVNPayPaymentDataSource;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.QPayment;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.PaymentReceiverRepository;
import com.cplerings.core.infrastructure.repository.PaymentRepository;
import com.cplerings.core.infrastructure.repository.VNPayTransactionRepository;
import com.cplerings.core.infrastructure.service.payment.datasource.VNPayPaymentServiceDataSource;

import lombok.RequiredArgsConstructor;

import com.querydsl.core.types.dsl.Expressions;

import java.util.Optional;

@DataSource
@RequiredArgsConstructor
public class SharedPaymentDataSource extends AbstractDataSource
        implements VNPayPaymentServiceDataSource, ProcessVNPayPaymentDataSource {

    private static final QPayment Q_PAYMENT = QPayment.payment;

    private final PaymentRepository paymentRepository;
    private final VNPayTransactionRepository vnPayTransactionRepository;
    private final PaymentReceiverRepository paymentReceiverRepository;

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
        return paymentRepository.findById(paymentId);
    }

    @Override
    public void save(VNPayTransaction vnPayTransaction) {
        updateAuditor(vnPayTransaction);
        vnPayTransactionRepository.save(vnPayTransaction);
    }

    @Override
    public Optional<PaymentReceiver> findPaymentReceiverByPaymentId(Long paymentId) {
        return paymentReceiverRepository.findByPaymentId(paymentId);
    }
}
