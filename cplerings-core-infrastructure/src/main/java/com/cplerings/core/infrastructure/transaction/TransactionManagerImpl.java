package com.cplerings.core.infrastructure.transaction;

import com.cplerings.core.application.shared.transaction.Session;
import com.cplerings.core.application.shared.transaction.SessionInformation;
import com.cplerings.core.application.shared.transaction.TransactionManager;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TransactionManagerImpl implements TransactionManager {

    private final PlatformTransactionManager transactionManager;
    private final SessionMapper sessionMapper;

    @Override
    public Session createSession(SessionInformation sessionInformation) {
        Objects.requireNonNull(sessionInformation, "session information cannot be null");
        final TransactionStatus status = transactionManager.getTransaction(sessionMapper.toDefinition(sessionInformation));
        return new SessionImpl(transactionManager, status);
    }
}
