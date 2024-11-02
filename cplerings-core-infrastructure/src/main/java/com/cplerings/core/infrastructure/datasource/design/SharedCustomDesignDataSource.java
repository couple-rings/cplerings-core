package com.cplerings.core.infrastructure.datasource.design;

import com.cplerings.core.application.design.datasource.CreateCustomDesignDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.QCustomDesign;
import com.cplerings.core.domain.design.QDesignVersion;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.spouse.QSpouse;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.CustomDesignRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DataSource
@RequiredArgsConstructor
public class SharedCustomDesignDataSource extends AbstractDataSource implements CreateCustomDesignDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QSpouse Q_SPOUSE = QSpouse.spouse;
    private static final QDesignVersion Q_DESIGN_VERSION = QDesignVersion.designVersion;
    private static final QCustomDesign Q_CUSTOM_DESIGN = QCustomDesign.customDesign;

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

    @Override
    public Optional<CustomDesign> getCustomDesignBySpouseId(long spouseId) {
        return Optional.ofNullable(createQuery().select(Q_CUSTOM_DESIGN)
                .from(Q_CUSTOM_DESIGN)
                .where(Q_CUSTOM_DESIGN.spouse.id.eq(spouseId)
                        .and(Q_CUSTOM_DESIGN.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public Optional<CustomDesign> getCustomDesignByDesignVersionId(long designVersionId) {
        return Optional.ofNullable(createQuery().select(Q_CUSTOM_DESIGN)
                .from(Q_CUSTOM_DESIGN)
                .where(Q_CUSTOM_DESIGN.designVersion.id.eq(designVersionId)
                        .and(Q_CUSTOM_DESIGN.state.eq(State.ACTIVE)))
                .fetchOne());
    }
}
