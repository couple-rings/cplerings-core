package com.cplerings.core.infrastructure.datasource.service.verification;

import com.cplerings.core.application.account.datasource.ResendCustomerVerificationCodeDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.QAccountVerification;
import com.cplerings.core.domain.account.VerificationCodeStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountVerificationRepository;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@DataSource
@RequiredArgsConstructor
public class ResendCustomerVerificationCodeDataSourceImpl extends AbstractDataSource implements ResendCustomerVerificationCodeDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QAccountVerification Q_ACCOUNT_VERIFICATION = QAccountVerification.accountVerification;

    private final AccountVerificationRepository accountVerificationRepository;

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
        final Collection<AccountVerification> oldAccountVerifications = createQuery().select(Q_ACCOUNT_VERIFICATION)
                .from(Q_ACCOUNT_VERIFICATION)
                .where(Q_ACCOUNT_VERIFICATION.account.id.eq(accountId)
                        .and(Q_ACCOUNT_VERIFICATION.status.eq(VerificationCodeStatus.PENDING)))
                .fetch();
        oldAccountVerifications.forEach(accountVerification -> {
            accountVerification.setState(State.INACTIVE);
            updateAuditor(accountVerification);
        });
        accountVerificationRepository.saveAll(oldAccountVerifications);
    }
}
