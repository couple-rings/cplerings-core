package com.cplerings.core.infrastructure.datasource.crafting;

import com.cplerings.core.application.crafting.datasource.AcceptCraftingRequestDataSource;
import com.cplerings.core.application.crafting.datasource.CompleteCraftingStageDataSource;
import com.cplerings.core.application.crafting.datasource.CreateCraftingRequestDataSource;
import com.cplerings.core.application.crafting.datasource.DepositCraftingStageDataSource;
import com.cplerings.core.application.crafting.datasource.ProcessCraftingStageDepositDataSource;
import com.cplerings.core.application.crafting.datasource.ViewCraftingRequestDataSource;
import com.cplerings.core.application.crafting.datasource.ViewCraftingRequestsDataSource;
import com.cplerings.core.application.crafting.datasource.result.CraftingRequests;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.branch.QBranch;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.configuration.QConfiguration;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.QCraftingStage;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.QCustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.QCraftingRequest;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.diamond.QDiamondSpecification;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.file.QDocument;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.spouse.QSpouse;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.ContractRepository;
import com.cplerings.core.infrastructure.repository.CraftingRequestRepository;
import com.cplerings.core.infrastructure.repository.CraftingStageRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.infrastructure.repository.PaymentReceiverRepository;
import com.cplerings.core.infrastructure.repository.RingRepository;

import lombok.RequiredArgsConstructor;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;

import java.util.List;
import java.util.Optional;

@DataSource
@RequiredArgsConstructor
public class SharedCraftingDataSource extends AbstractDataSource
        implements CreateCraftingRequestDataSource, AcceptCraftingRequestDataSource, DepositCraftingStageDataSource,
        ProcessCraftingStageDepositDataSource, ViewCraftingRequestsDataSource, CompleteCraftingStageDataSource, ViewCraftingRequestDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QDiamondSpecification Q_DIAMOND_SPECIFICATION = QDiamondSpecification.diamondSpecification;
    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;
    private static final QCustomDesign Q_CUSTOM_DESIGN = QCustomDesign.customDesign;
    private static final QCraftingRequest Q_CRAFTING_REQUEST = QCraftingRequest.craftingRequest;
    private static final QSpouse Q_SPOUSE = QSpouse.spouse;
    private static final QDocument Q_DOCUMENT = QDocument.document;
    private static final QBranch Q_BRANCH = QBranch.branch;
    private static final QConfiguration Q_CONFIGURATION = QConfiguration.configuration;
    private static final QCraftingStage Q_CRAFTING_STAGE = QCraftingStage.craftingStage;

    private static final String SIDE_DIAMOND_PRICE = "DEFE";

    private final CraftingRequestRepository craftingRequestRepository;
    private final RingRepository ringRepository;
    private final ContractRepository contractRepository;
    private final CustomOrderRepository customOrderRepository;
    private final CraftingStageRepository craftingStageRepository;
    private final PaymentReceiverRepository paymentReceiverRepository;
    private final ImageRepository imageRepository;

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
    public Optional<CraftingStage> findCraftingStageById(Long craftingStageId) {
        return Optional.ofNullable(createQuery().select(Q_CRAFTING_STAGE)
                .from(Q_CRAFTING_STAGE)
                .leftJoin(Q_CRAFTING_STAGE.customOrder).fetchJoin()
                .leftJoin(Q_CRAFTING_STAGE.customOrder.craftingStages).fetchJoin()
                .where(Q_CRAFTING_STAGE.id.eq(craftingStageId))
                .fetchFirst());
    }

    @Override
    public Optional<Image> findImageById(Long imageId) {
        return imageRepository.findById(imageId);
    }

    @Override
    public PaymentReceiver save(PaymentReceiver paymentReceiver) {
        updateAuditor(paymentReceiver);
        return paymentReceiverRepository.save(paymentReceiver);
    }

    @Override
    public Optional<CraftingStage> findById(Long craftingStageId) {
        return findCraftingStageById(craftingStageId);
    }

    @Override
    public CraftingStage save(CraftingStage craftingStage) {
        updateAuditor(craftingStage);
        return craftingStageRepository.save(craftingStage);
    }

    @Override
    public CustomOrder save(CustomOrder customOrder) {
        updateAuditor(customOrder);
        return customOrderRepository.save(customOrder);
    }

    @Override
    public CraftingRequests getCraftingrequests(ViewCraftingRequestsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<CraftingRequest> query = createQuery()
                .select(Q_CRAFTING_REQUEST)
                .from(Q_CRAFTING_REQUEST);
        long count = query.distinct().fetchCount();
        List<CraftingRequest> craftingRequests = query.limit(input.getPageSize()).offset(offset).fetch();
        return CraftingRequests.builder()
                .craftingRequests(craftingRequests)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Optional<CraftingRequest> getCraftingRequest(Long craftingRequestId) {
        return Optional.ofNullable(createQuery().select(Q_CRAFTING_REQUEST)
                .from(Q_CRAFTING_REQUEST)
                .where(Q_CRAFTING_REQUEST.id.eq(craftingRequestId))
                .fetchOne());
    }
}
