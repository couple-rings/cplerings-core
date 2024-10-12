package com.cplerings.core.infrastructure.datasource.account;

import static com.querydsl.jpa.JPAExpressions.select;

import java.util.Optional;

import com.cplerings.core.application.account.datasource.RegisterCustomerDataSource;
import com.cplerings.core.application.account.datasource.RequestResetPasswordDataSource;
import com.cplerings.core.application.account.datasource.ResetPasswordDataSource;
import com.cplerings.core.application.account.datasource.VerifyCustomerDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountPasswordReset;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.QAccountPasswordReset;
import com.cplerings.core.domain.account.QAccountVerification;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountPasswordResetRepository;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.AccountVerificationRepository;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedAccountDataSource extends AbstractDataSource
        implements RegisterCustomerDataSource, VerifyCustomerDataSource, RequestResetPasswordDataSource, ResetPasswordDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QAccountVerification Q_ACCOUNT_VERIFICATION = QAccountVerification.accountVerification;
    private static final QAccountPasswordReset Q_ACCOUNT_PASSWORD_RESET = QAccountPasswordReset.accountPasswordReset;

    private final AccountRepository accountRepository;
    private final AccountVerificationRepository accountVerificationRepository;
    private final AccountPasswordResetRepository accountPasswordResetRepository;

    @Override
    public boolean emailIsNew(String email) {
        return !accountRepository.existsByEmail(email);
    }

    @Override
    public boolean usernameIsNew(String username) {
        return !accountRepository.existsByUsername(username);
    }

    @Override
    public Account save(Account account) {
        updateAuditor(account);
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountByEmailWithMostRecentVerificationCode(String email) {
        final QAccountVerification accountVerificationSubQuery = new QAccountVerification("accountVerificationSubQuery");
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .leftJoin(Q_ACCOUNT.verification, Q_ACCOUNT_VERIFICATION)
                .on(Q_ACCOUNT_VERIFICATION.account.eq(Q_ACCOUNT)
                        .and(Q_ACCOUNT_VERIFICATION.id.in(select(accountVerificationSubQuery.id)
                                .from(accountVerificationSubQuery)
                                .orderBy(accountVerificationSubQuery.id.desc())
                                .limit(1))))
                .fetchJoin()
                .where(Q_ACCOUNT.email.eq(email))
                .fetchFirst());
    }

    @Override
    public void saveAccountVerification(AccountVerification accountVerification) {
        updateAuditor(accountVerification);
        accountVerificationRepository.save(accountVerification);
    }

    @Override
    public Account saveAccount(Account account) {
        return save(account);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public void save(AccountPasswordReset accountPasswordReset) {
        updateAuditor(accountPasswordReset);
        accountPasswordResetRepository.save(accountPasswordReset);
    }

    @Override
    public Optional<Account> findAccountByEmailWithMostRecentResetPasswordOTP(String email) {
        final QAccountPasswordReset accountPasswordResetSubQuery = new QAccountPasswordReset("accountPasswordResetSubQuery");
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .leftJoin(Q_ACCOUNT.passwordResets, Q_ACCOUNT_PASSWORD_RESET)
                .on(Q_ACCOUNT_PASSWORD_RESET.account.eq(Q_ACCOUNT)
                        .and(Q_ACCOUNT_PASSWORD_RESET.id.in(select(accountPasswordResetSubQuery.id)
                                .from(accountPasswordResetSubQuery)
                                .orderBy(accountPasswordResetSubQuery.id.desc())
                                .limit(1))))
                .fetchJoin()
                .where(Q_ACCOUNT.email.eq(email))
                .fetchFirst());
    }
}
