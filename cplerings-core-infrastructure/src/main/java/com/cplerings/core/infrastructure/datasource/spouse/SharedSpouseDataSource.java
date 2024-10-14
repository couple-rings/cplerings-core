package com.cplerings.core.infrastructure.datasource.spouse;

import java.util.Optional;

import com.cplerings.core.application.spouse.datasource.CreateSpouseDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.spouse.QSpouse;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.SpouseAccountRepository;
import com.cplerings.core.infrastructure.repository.SpouseRepository;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedSpouseDataSource extends AbstractDataSource
        implements CreateSpouseDataSource {

    private static final QSpouse Q_SPOUSE = QSpouse.spouse;
    private static final QAccount Q_ACCOUNT = QAccount.account;
    private final SpouseRepository spouseRepository;
    private final SpouseAccountRepository spouseAccountRepository;

    @Override
    public Optional<Spouse> getSpouseByCitizenId(String citizenId) {
        return Optional.ofNullable(createQuery()
                .select(Q_SPOUSE)
                .from(Q_SPOUSE)
                .where(Q_SPOUSE.citizenId.eq(citizenId))
                .fetchOne());
    }

    @Override
    public Spouse save(Spouse spouse) {
        updateAuditor(spouse);
        return spouseRepository.save(spouse);
    }

    @Override
    public void save(SpouseAccount spouseAccount) {
        updateAuditor(spouseAccount);
        spouseAccountRepository.save(spouseAccount);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(id))
                .fetchOne());
    }
}
