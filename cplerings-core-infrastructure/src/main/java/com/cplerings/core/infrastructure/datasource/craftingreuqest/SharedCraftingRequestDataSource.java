package com.cplerings.core.infrastructure.datasource.craftingreuqest;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.application.craftingrequest.datasource.AcceptCraftingRequestDataSource;
import com.cplerings.core.application.craftingrequest.datasource.CreateCraftingRequestDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.branch.QBranch;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.configuration.QConfiguration;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.QCustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.QCraftingRequest;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.diamond.QDiamondSpecification;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.QDocument;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.ring.QRing;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.spouse.QSpouse;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.ContractRepository;
import com.cplerings.core.infrastructure.repository.CraftingRequestRepository;
import com.cplerings.core.infrastructure.repository.CraftingStageRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderRepository;
import com.cplerings.core.infrastructure.repository.RingRepository;
import com.querydsl.jpa.impl.JPAUpdateClause;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedCraftingRequestDataSource extends AbstractDataSource implements CreateCraftingRequestDataSource, AcceptCraftingRequestDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QDiamondSpecification Q_DIAMOND_SPECIFICATION = QDiamondSpecification.diamondSpecification;
    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;
    private static final QCustomDesign Q_CUSTOM_DESIGN = QCustomDesign.customDesign;
    private static final QCraftingRequest Q_CRAFTING_REQUEST = QCraftingRequest.craftingRequest;
    private static final QSpouse Q_SPOUSE = QSpouse.spouse;
    private static final QDocument Q_DOCUMENT = QDocument.document;
    private static final QBranch Q_BRANCH = QBranch.branch;
    private static final QConfiguration Q_CONFIGURATION = QConfiguration.configuration;
    private static final QRing Q_RING = QRing.ring;

    private static final String SIDE_DIAMOND_PRICE = "DEFE";

    @PersistenceContext
    private EntityManager entityManager;

    private final CraftingRequestRepository craftingRequestRepository;
    private final RingRepository ringRepository;
    private final ContractRepository contractRepository;
    private final CustomOrderRepository customOrderRepository;
    private final CraftingStageRepository craftingStageRepository;

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

    @Override
    public Optional<CraftingRequest> getCraftingRequestById(Long craftingRequestId) {
        return Optional.ofNullable(createQuery().select(Q_CRAFTING_REQUEST)
                .from(Q_CRAFTING_REQUEST)
                .leftJoin(Q_CRAFTING_REQUEST.customDesign, Q_CUSTOM_DESIGN).fetchJoin()
                .leftJoin(Q_CRAFTING_REQUEST.metalSpecification, Q_METAL_SPECIFICATION).fetchJoin()
                .leftJoin(Q_CRAFTING_REQUEST.diamondSpecification, Q_DIAMOND_SPECIFICATION).fetchJoin()
                .leftJoin(Q_CUSTOM_DESIGN.spouse, Q_SPOUSE).fetchJoin()
                .where(Q_CRAFTING_REQUEST.id.eq(craftingRequestId))
                .fetchOne());
    }

    @Override
    public List<Ring> saveRings(List<Ring> rings) {
        rings.forEach(this::updateAuditor);
        return ringRepository.saveAll(rings);
    }

    @Override
    public Document getMaintenanceDocument() {
        return createQuery().select(Q_DOCUMENT)
                .from(Q_DOCUMENT)
                .where(Q_DOCUMENT.id.eq(1L)).fetchOne();
    }

    @Override
    public Contract saveContract(Contract contract) {
        updateAuditor(contract);
        return contractRepository.save(contract);
    }

    @Override
    public CustomOrder saveCustomOrder(CustomOrder customOrder) {
        updateAuditor(customOrder);
        return customOrderRepository.save(customOrder);
    }

    @Override
    public List<CraftingRequest> saveCraftingRequests(List<CraftingRequest> craftingRequests) {
        craftingRequests.forEach(this::updateAuditor);
        return craftingRequestRepository.saveAll(craftingRequests);
    }

    @Override
    public Optional<Branch> getBranchById(Long branchId) {
        return Optional.ofNullable(createQuery().select(Q_BRANCH)
                .from(Q_BRANCH)
                .where(Q_BRANCH.id.eq(branchId))
                .fetchOne());
    }

    @Override
    public Configuration getConfigurationForSideDiamond() {
        return createQuery().select(Q_CONFIGURATION)
                .from(Q_CONFIGURATION)
                .where(Q_CONFIGURATION.key.eq(SIDE_DIAMOND_PRICE))
                .fetchOne();
    }

    @Override
    public void saveStages(List<CraftingStage> craftingStages) {
        craftingStages.forEach(this::updateAuditor);
        craftingStageRepository.saveAll(craftingStages);
    }

    @Override
    public void updateRingWithCustomOrder(Long ringId, CustomOrder customOrder) {
         new JPAUpdateClause(entityManager, Q_RING)
                .where(Q_RING.id.eq(ringId))
                .set(Q_RING.customOrder, customOrder)
                .execute();
    }
}
