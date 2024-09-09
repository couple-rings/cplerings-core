package com.cplerings.core.infrastructure.transaction;

import com.cplerings.core.application.shared.transaction.Session;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import java.util.Objects;

public final class SessionImpl implements Session {

    private final PlatformTransactionManager transactionManager;
    private final TransactionStatus status;

    public SessionImpl(PlatformTransactionManager transactionManager, TransactionStatus status) {
        this.transactionManager = Objects.requireNonNull(transactionManager, "Transaction manager must not be null");
        this.status = Objects.requireNonNull(status, "Status must not be null");
    }

    @Override
    public void commit() {
        transactionManager.commit(status);
    }

    @Override
    public void rollback() {
        transactionManager.rollback(status);
    }
}
