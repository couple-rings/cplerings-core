package com.cplerings.core.infrastructure.datasource.design;

import java.util.Optional;

import com.cplerings.core.application.design.datasource.CreateCustomDesignDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.QDesignVersion;
import com.cplerings.core.domain.spouse.QSpouse;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.CustomDesignRepository;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedCustomDesignDataSource extends AbstractDataSource implements CreateCustomDesignDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QSpouse Q_SPOUSE = QSpouse.spouse;
    private static final QDesignVersion Q_DESIGN_VERSION = QDesignVersion.designVersion;

    private final CustomDesignRepository customDesignRepository;

    @Override
    public CustomDesign save(CustomDesign customDesign) {
        updateAuditor(customDesign);
        return customDesignRepository.save(customDesign);
    }

    @Override
    public Optional<Account> getCustomerById(long customerId) {
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(customerId))
                .fetchOne());
    }

    @Override
    public Optional<Spouse> getSpouseById(long spouseId) {
        return Optional.ofNullable(createQuery().select(Q_SPOUSE)
                .from(Q_SPOUSE)
                .where(Q_SPOUSE.id.eq(spouseId))
                .fetchOne());
    }

    @Override
    public Optional<DesignVersion> getDesignVersionById(long designVersionId) {
        return Optional.ofNullable(createQuery().select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION)
                .where(Q_DESIGN_VERSION.id.eq(designVersionId))
                .fetchOne());
    }
}
