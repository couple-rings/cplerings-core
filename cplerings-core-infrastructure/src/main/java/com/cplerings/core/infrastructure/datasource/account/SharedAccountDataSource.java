package com.cplerings.core.infrastructure.datasource.account;

import static com.querydsl.jpa.JPAExpressions.select;

import com.cplerings.core.application.account.datasource.RegisterCustomerDataSource;
import com.cplerings.core.application.account.datasource.VerifyCustomerDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.QAccountVerification;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DataSource
@RequiredArgsConstructor
public class SharedAccountDataSource extends AbstractDataSource
        implements RegisterCustomerDataSource, VerifyCustomerDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QAccountVerification Q_ACCOUNT_VERIFICATION = QAccountVerification.accountVerification;

    private final AccountRepository accountRepository;

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
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .leftJoin(Q_ACCOUNT.verification, Q_ACCOUNT_VERIFICATION)
                .on(Q_ACCOUNT_VERIFICATION.account.eq(Q_ACCOUNT)
                        .and(Q_ACCOUNT_VERIFICATION.id.in(select(Q_ACCOUNT_VERIFICATION.id)
                                .from(Q_ACCOUNT_VERIFICATION)
                                .orderBy(Q_ACCOUNT_VERIFICATION.id.desc())
                                .limit(1))))
                .fetchJoin()
                .where(Q_ACCOUNT.email.eq(email))
                .fetchFirst());
    }
}
