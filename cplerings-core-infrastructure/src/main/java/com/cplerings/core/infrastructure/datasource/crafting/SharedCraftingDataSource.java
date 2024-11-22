package com.cplerings.core.infrastructure.datasource.crafting;

import static com.cplerings.core.domain.design.QCustomDesign.customDesign;

import com.cplerings.core.application.crafting.datasource.AcceptCraftingRequestDataSource;
import com.cplerings.core.application.crafting.datasource.CompleteCraftingStageDataSource;
import com.cplerings.core.application.crafting.datasource.CreateCraftingRequestDataSource;
import com.cplerings.core.application.crafting.datasource.DepositCraftingStageDataSource;
import com.cplerings.core.application.crafting.datasource.ProcessCraftingStageDepositDataSource;
import com.cplerings.core.application.crafting.datasource.ViewCraftingRequestDataSource;
import com.cplerings.core.application.crafting.datasource.ViewCraftingRequestsDataSource;
import com.cplerings.core.application.crafting.datasource.ViewCraftingRequestsGroupsDataSource;
import com.cplerings.core.application.crafting.datasource.ViewCraftingStagesDataSource;
import com.cplerings.core.application.crafting.datasource.result.CraftingRequestGroupsList;
import com.cplerings.core.application.crafting.datasource.result.CraftingRequests;
import com.cplerings.core.application.crafting.datasource.result.CraftingStages;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsGroupsInput;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsInput;
import com.cplerings.core.application.crafting.input.ViewCraftingStagesInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.branch.QBranch;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.configuration.QConfiguration;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.QCraftingStage;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.QCustomDesign;
import com.cplerings.core.domain.design.QDesign;
import com.cplerings.core.domain.design.QDesignVersion;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.domain.design.crafting.QCraftingRequest;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.QCustomRequest;
import com.cplerings.core.domain.design.request.QDesignCustomRequest;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.diamond.QDiamond;
import com.cplerings.core.domain.diamond.QDiamondSpecification;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.file.QDocument;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.ring.QRingDiamond;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.spouse.Agreement;
import com.cplerings.core.domain.spouse.QSpouse;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AgreementRepository;
import com.cplerings.core.infrastructure.repository.ContractRepository;
import com.cplerings.core.infrastructure.repository.CraftingRequestRepository;
import com.cplerings.core.infrastructure.repository.CraftingStageRepository;
import com.cplerings.core.infrastructure.repository.CustomDesignRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderRepository;
import com.cplerings.core.infrastructure.repository.CustomRequestRepository;
import com.cplerings.core.infrastructure.repository.DiamondRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.infrastructure.repository.PaymentReceiverRepository;
import com.cplerings.core.infrastructure.repository.RingDiamondRepository;
import com.cplerings.core.infrastructure.repository.RingRepository;
import com.cplerings.core.infrastructure.repository.TransportationAddressRepository;
import com.cplerings.core.infrastructure.repository.TransportationOrderRepository;

import lombok.RequiredArgsConstructor;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataSource
@RequiredArgsConstructor
public class SharedCraftingDataSource extends AbstractDataSource
        implements CreateCraftingRequestDataSource, AcceptCraftingRequestDataSource, DepositCraftingStageDataSource,
        ProcessCraftingStageDepositDataSource, ViewCraftingRequestsDataSource, CompleteCraftingStageDataSource, ViewCraftingRequestDataSource,
        ViewCraftingRequestsGroupsDataSource, ViewCraftingStagesDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QDiamondSpecification Q_DIAMOND_SPECIFICATION = QDiamondSpecification.diamondSpecification;
    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;
    private static final QCustomDesign Q_CUSTOM_DESIGN = customDesign;
    private static final QCraftingRequest Q_CRAFTING_REQUEST = QCraftingRequest.craftingRequest;
    private static final QSpouse Q_SPOUSE = QSpouse.spouse;
    private static final QDocument Q_DOCUMENT = QDocument.document;
    private static final QBranch Q_BRANCH = QBranch.branch;
    private static final QConfiguration Q_CONFIGURATION = QConfiguration.configuration;
    private static final QCraftingStage Q_CRAFTING_STAGE = QCraftingStage.craftingStage;
    private static final QDesignVersion Q_DESIGN_VERSION = QDesignVersion.designVersion;
    private static final QDesign Q_DESIGN = QDesign.design;
    private static final QDesignCustomRequest Q_DESIGN_CUSTOM_REQUEST = QDesignCustomRequest.designCustomRequest;
    private static final QCustomRequest Q_CUSTOM_REQUEST = QCustomRequest.customRequest;
    private static final QDiamond Q_DIAMOND = QDiamond.diamond;
    private static final QRingDiamond Q_RING_DIAMOND = QRingDiamond.ringDiamond;

    private static final String SIDE_DIAMOND_PRICE = "SDPR";

    private final CraftingRequestRepository craftingRequestRepository;
    private final RingRepository ringRepository;
    private final ContractRepository contractRepository;
    private final CustomOrderRepository customOrderRepository;
    private final CraftingStageRepository craftingStageRepository;
    private final PaymentReceiverRepository paymentReceiverRepository;
    private final ImageRepository imageRepository;
    private final TransportationAddressRepository transportationAddressRepository;
    private final TransportationOrderRepository transportationOrderRepository;
    private final AgreementRepository agreementRepository;
    private final DocumentRepository documentRepository;
    private final CustomDesignRepository customDesignRepository;
    private final CustomRequestRepository customRequestRepository;
    private final RingDiamondRepository ringDiamondRepository;
    private final DiamondRepository diamondRepository;

    @Override
    public Optional<Account> getAccountByCustomerId(Long customerId) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .leftJoin(Q_ACCOUNT.craftingRequests, Q_CRAFTING_REQUEST).fetchJoin()
                .leftJoin(Q_CRAFTING_REQUEST.branch).fetchJoin()
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
                .leftJoin(Q_CUSTOM_DESIGN.designVersion, Q_DESIGN_VERSION).fetchJoin()
                .leftJoin(Q_DESIGN_VERSION.design, Q_DESIGN).fetchJoin()
                .leftJoin(Q_DESIGN.designCustomRequests, Q_DESIGN_CUSTOM_REQUEST).fetchJoin()
                .leftJoin(Q_DESIGN_CUSTOM_REQUEST.customRequest, Q_CUSTOM_REQUEST).fetchJoin()
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
    public void save(CustomDesign customDesign) {
        updateAuditor(customDesign);
        customDesignRepository.save(customDesign);
    }

    @Override
    public void save(CustomRequest customRequest) {
        updateAuditor(customRequest);
        customRequestRepository.save(customRequest);
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
    public Optional<TransportationAddress> findTransportAddressById(Long transportAddressId) {
        return transportationAddressRepository.findById(transportAddressId);
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
    public Set<Document> getMaintenancesByIds(Collection<Long> maintenanceIds) {
        return new HashSet<>(documentRepository.findAllById(maintenanceIds));
    }

    @Override
    public Ring save(Ring ring) {
        updateAuditor(ring);
        return ringRepository.save(ring);
    }

    @Override
    public Collection<Diamond> getUnusedDiamondsFromSpecsAndBranch(Collection<Long> diamondSpecIds, Long branchId) {
        return new HashSet<>(createQuery().select(Q_DIAMOND)
                .from(Q_DIAMOND)
                .where(Q_DIAMOND.state.eq(State.ACTIVE)
                        .and(Q_DIAMOND.branch.id.eq(branchId))
                        .and(Q_DIAMOND.diamondSpecification.id.in(diamondSpecIds))
                        .and(JPAExpressions.selectOne()
                                .from(Q_RING_DIAMOND)
                                .where(Q_RING_DIAMOND.diamond.eq(Q_DIAMOND)
                                        .and(Q_RING_DIAMOND.state.eq(State.ACTIVE)))
                                .notExists()))
                .distinct()
                .limit(2)
                .fetch());
    }

    @Override
    public Optional<Diamond> getUnusedDiamondFromSpecAndBranch(Long diamondSpecId, Long branchId) {
        return Optional.ofNullable(createQuery().select(Q_DIAMOND)
                .from(Q_DIAMOND)
                .where(Q_DIAMOND.state.eq(State.ACTIVE)
                        .and(Q_DIAMOND.branch.id.eq(branchId))
                        .and(Q_DIAMOND.diamondSpecification.id.eq(diamondSpecId))
                        .and(JPAExpressions.selectOne()
                                .from(Q_RING_DIAMOND)
                                .where(Q_RING_DIAMOND.diamond.eq(Q_DIAMOND)
                                        .and(Q_RING_DIAMOND.state.eq(State.ACTIVE)))
                                .notExists()))
                .fetchFirst());
    }

    @Override
    public RingDiamond save(RingDiamond ringDiamond) {
        updateAuditor(ringDiamond);
        return ringDiamondRepository.save(ringDiamond);
    }

    @Override
    public Diamond save(Diamond diamond) {
        updateAuditor(diamond);
        return diamondRepository.save(diamond);
    }

    @Override
    public TransportationOrder save(TransportationOrder transportationOrder) {
        updateAuditor(transportationOrder);
        return transportationOrderRepository.save(transportationOrder);
    }

    @Override
    public Agreement save(Agreement agreement) {
        updateAuditor(agreement);
        return agreementRepository.save(agreement);
    }

    @Override
    public CraftingRequests getCraftingrequests(ViewCraftingRequestsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<CraftingRequest> query = createQuery()
                .select(Q_CRAFTING_REQUEST)
                .from(Q_CRAFTING_REQUEST);

        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();
        if (input.getCustomDesignId() != null) {
            booleanExpressionBuilder.and(Q_CRAFTING_REQUEST.customDesign.id.eq(input.getCustomDesignId()));
        }

        if (input.getCustomerId() != null) {
            booleanExpressionBuilder.and(Q_CRAFTING_REQUEST.customer.id.eq(input.getCustomerId()));
        }

        if (input.getStatus() != null) {
            switch (input.getStatus()) {
                case PENDING ->
                        booleanExpressionBuilder.and(Q_CRAFTING_REQUEST.craftingRequestStatus.eq(CraftingRequestStatus.PENDING));
                case ACCEPTED ->
                        booleanExpressionBuilder.and(Q_CRAFTING_REQUEST.craftingRequestStatus.eq(CraftingRequestStatus.ACCEPTED));
                case REJECTED ->
                        booleanExpressionBuilder.and(Q_CRAFTING_REQUEST.craftingRequestStatus.eq(CraftingRequestStatus.REJECTED));
            }
        }
        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);

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

    @Override
    public CraftingRequestGroupsList getAccountCraftingRequests(ViewCraftingRequestsGroupsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Account> query = createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .rightJoin(Q_ACCOUNT.craftingRequests, Q_CRAFTING_REQUEST).fetchJoin()
                .where(Q_ACCOUNT.role.eq(Role.CUSTOMER));
        long count = query.distinct().fetchCount();
        List<Account> accounts = query.limit(input.getPageSize()).offset(offset).fetch();
        return CraftingRequestGroupsList.builder()
                .customerCraftingRequests(accounts)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public CraftingStages getCraftingStages(ViewCraftingStagesInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<CraftingStage> query = createQuery()
                .select(Q_CRAFTING_STAGE)
                .from(Q_CRAFTING_STAGE);

        if (input.getCustomOrderId() != null) {
            query.where(Q_CRAFTING_STAGE.customOrder.id.eq(input.getCustomOrderId()));
        }
        long count = query.distinct().fetchCount();
        List<CraftingStage> craftingStages = query.limit(input.getPageSize()).offset(offset).fetch();
        return CraftingStages.builder()
                .craftingStages(craftingStages)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
