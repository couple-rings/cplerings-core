package com.cplerings.core.infrastructure.datasource.spouse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cplerings.core.application.spouse.datasource.CreateSpouseDataSource;
import com.cplerings.core.application.spouse.datasource.VerifyResidentIdDataSource;
import com.cplerings.core.application.spouse.datasource.ViewSpousesOfCustomerDataSource;
import com.cplerings.core.application.spouse.input.VerifyResidentIdInput;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.spouse.QSpouse;
import com.cplerings.core.domain.spouse.QSpouseAccount;
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
        implements CreateSpouseDataSource, ViewSpousesOfCustomerDataSource, VerifyResidentIdDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QSpouse Q_SPOUSE = QSpouse.spouse;
    private static final QSpouseAccount Q_SPOUSE_ACCOUNT = QSpouseAccount.spouseAccount;

    private final SpouseRepository spouseRepository;
    private final SpouseAccountRepository spouseAccountRepository;

    @Override
    public boolean doesNotExistSpouseByCitizenIdIn(Collection<String> citizenIds) {
        return !spouseRepository.existsByCitizenIdIn(citizenIds);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(id))
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
    public Optional<Spouse> getSpousesByCustomerId(Long customerId) {
        return Optional.ofNullable(createQuery()
                .select(Q_SPOUSE)
                .distinct()
                .from(Q_SPOUSE)
                .where(Q_SPOUSE.spouseAccount.customer.id.eq(customerId))
                .fetchOne());
    }

    @Override
    public Optional<Account> getCustomerById(Long customerId) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(customerId))
                .fetchOne());
    }

    @Override
    public List<Spouse> getSpouseByCoupleId(UUID coupleId) {
        return createQuery()
                .select(Q_SPOUSE)
                .from(Q_SPOUSE)
                .where(Q_SPOUSE.coupleId.eq(coupleId))
                .fetch();
    }

    @Override
    public Optional<Spouse> getSpouseByCitizenId(VerifyResidentIdInput input) {
        return Optional.ofNullable(createQuery()
                .select(Q_SPOUSE)
                .from(Q_SPOUSE)
                .where(Q_SPOUSE.citizenId.eq(input.citizenId()))
                .fetchFirst());
    }

    @Override
    public Optional<Account> getCustomerWithSpouseById(Long customerId) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .leftJoin(Q_ACCOUNT.spouse, Q_SPOUSE_ACCOUNT).fetchJoin()
                .leftJoin(Q_SPOUSE_ACCOUNT.spouse, Q_SPOUSE).fetchJoin()
                .where(Q_ACCOUNT.id.eq(customerId))
                .fetchFirst());
    }
}
