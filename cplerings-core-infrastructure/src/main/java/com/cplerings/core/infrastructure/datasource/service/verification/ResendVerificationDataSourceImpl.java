package com.cplerings.core.infrastructure.datasource.service.verification;

import java.util.Optional;

import com.cplerings.core.application.verification.datasource.ResendVerificationDataSource;
import com.cplerings.core.domain.State;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.QAccountVerification;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.querydsl.jpa.impl.JPAUpdateClause;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class ResendVerificationDataSourceImpl extends AbstractDataSource implements ResendVerificationDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QAccountVerification Q_ACCOUNT_VERIFICATION = QAccountVerification.accountVerification;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.email.eq(email))
                .fetchOne());
    }

    @Override
    public void disableAllPreviousCodes(Long accountId) {
        new JPAUpdateClause(entityManager, Q_ACCOUNT_VERIFICATION)
                .set(Q_ACCOUNT_VERIFICATION.state, State.INACTIVE)
                .where(Q_ACCOUNT_VERIFICATION.account.id.eq(accountId))
                .execute();
    }
}
