package com.cplerings.core.infrastructure.datasource.service.payment;

import com.cplerings.core.application.payment.datasource.ProcessVNPayPaymentDataSource;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.transaction.QVNPayTransaction;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
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

    private static final QVNPayTransaction Q_VN_PAY_TRANSACTION = QVNPayTransaction.vNPayTransaction;

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
                .from(Q_VN_PAY_TRANSACTION)
                .where(Q_VN_PAY_TRANSACTION.payment.id.eq(paymentId)
                        .and(Q_VN_PAY_TRANSACTION.payment.status.eq(PaymentStatus.PENDING)))
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
}
