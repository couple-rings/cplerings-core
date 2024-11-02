package com.cplerings.core.infrastructure.datasource.craftingreuqest;

import com.cplerings.core.application.craftingrequest.datasource.CreateCraftingRequestDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.QCustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.diamond.QDiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.CraftingRequestRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@DataSource
@RequiredArgsConstructor
public class SharedCraftingRequestDataSource extends AbstractDataSource implements CreateCraftingRequestDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QDiamondSpecification Q_DIAMOND_SPECIFICATION = QDiamondSpecification.diamondSpecification;
    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;
    private static final QCustomDesign Q_CUSTOM_DESIGN = QCustomDesign.customDesign;

    private final CraftingRequestRepository craftingRequestRepository;

    @Override
    public Optional<Account> getAccountByCustomerId(Long customerId) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(customerId)
                        .and(Q_ACCOUNT.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public Optional<DiamondSpecification> getDiamondSpecificationByDiamondSpecId(Long diamondSpecid) {
        return Optional.ofNullable(createQuery()
                .select(Q_DIAMOND_SPECIFICATION)
                .from(Q_DIAMOND_SPECIFICATION)
                .where(Q_DIAMOND_SPECIFICATION.id.eq(diamondSpecid)
                        .and(Q_DIAMOND_SPECIFICATION.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public Optional<MetalSpecification> getMetalSpecificationByMetalSpecId(Long metalSpecid) {
        return Optional.ofNullable(createQuery()
                .select(Q_METAL_SPECIFICATION)
                .from(Q_METAL_SPECIFICATION)
                .where(Q_METAL_SPECIFICATION.id.eq(metalSpecid)
                        .and(Q_METAL_SPECIFICATION.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public Optional<CustomDesign> getCustomDesignByCustomDesignId(Long customDesignId) {
        return Optional.ofNullable(createQuery()
                .select(Q_CUSTOM_DESIGN)
                .from(Q_CUSTOM_DESIGN)
                .where(Q_CUSTOM_DESIGN.id.eq(customDesignId)
                        .and(Q_CUSTOM_DESIGN.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public CraftingRequest save(CraftingRequest craftingRequest) {
        updateAuditor(craftingRequest);
        return craftingRequestRepository.save(craftingRequest);
    }

}
