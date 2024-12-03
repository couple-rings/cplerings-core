package com.cplerings.core.infrastructure.datasource.design;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.design.datasource.CheckRemainingDesignSessionDataSource;
import com.cplerings.core.application.design.datasource.CreateDesignDataSource;
import com.cplerings.core.application.design.datasource.CreateDesignSessionDataSource;
import com.cplerings.core.application.design.datasource.CreateDesignVersionDataSource;
import com.cplerings.core.application.design.datasource.DetermineDesignVersionDataSource;
import com.cplerings.core.application.design.datasource.ProcessDesignSessionPaymentDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignCollectionsDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignSessionsLeftDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignVersionDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignVersionsDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignsDataSource;
import com.cplerings.core.application.design.datasource.result.DesignCollections;
import com.cplerings.core.application.design.datasource.result.DesignVersions;
import com.cplerings.core.application.design.datasource.result.Designs;
import com.cplerings.core.application.design.input.ViewDesignCollectionsInput;
import com.cplerings.core.application.design.input.ViewDesignVersionsInput;
import com.cplerings.core.application.design.input.ViewDesignsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignCharacteristic;
import com.cplerings.core.domain.design.DesignCollection;
import com.cplerings.core.domain.design.DesignMetalSpecification;
import com.cplerings.core.domain.design.DesignStatus;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.QDesign;
import com.cplerings.core.domain.design.QDesignCollection;
import com.cplerings.core.domain.design.QDesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.QCustomRequest;
import com.cplerings.core.domain.design.request.QDesignCustomRequest;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;
import com.cplerings.core.domain.design.session.QDesignSession;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.file.QDocument;
import com.cplerings.core.domain.file.QImage;
import com.cplerings.core.domain.jewelry.JewelryCategory;
import com.cplerings.core.domain.jewelry.QJewelryCategory;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.domain.payment.DesignSessionPayment;
import com.cplerings.core.domain.shared.valueobject.DesignSize;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.CustomDesignMetalSpecificationRepository;
import com.cplerings.core.infrastructure.repository.CustomRequestRepository;
import com.cplerings.core.infrastructure.repository.DesignMetalSpecificationRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DesignSessionPaymentRepository;
import com.cplerings.core.infrastructure.repository.DesignSessionRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedDesignDataSource extends AbstractDataSource
        implements CreateDesignSessionDataSource, ProcessDesignSessionPaymentDataSource, CheckRemainingDesignSessionDataSource,
        CreateDesignVersionDataSource, ViewDesignVersionDataSource, ViewDesignVersionsDataSource, DetermineDesignVersionDataSource,
        ViewDesignSessionsLeftDataSource, ViewDesignDataSource, CreateDesignDataSource, ViewDesignsDataSource, ViewDesignCollectionsDataSource {

    private static final QDesign Q_DESIGN = QDesign.design;
    private static final QDesignVersion Q_DESIGN_VERSION = QDesignVersion.designVersion;
    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QCustomRequest Q_CUSTOM_REQUEST = QCustomRequest.customRequest;
    private static final QDesignCustomRequest Q_DESIGN_CUSTOM_REQUEST = QDesignCustomRequest.designCustomRequest;
    private static final QDocument Q_DOCUMENT = QDocument.document;
    private static final QImage Q_IMAGE = QImage.image;
    private static final QDesignSession Q_DESIGN_SESSION = QDesignSession.designSession;
    private static final QJewelryCategory Q_JEWELRY_CATEGORY = QJewelryCategory.jewelryCategory;
    private static final QDesignCollection Q_DESIGN_COLLECTION = QDesignCollection.designCollection;
    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;

    private final DesignSessionRepository designSessionRepository;
    private final AccountRepository accountRepository;
    private final CustomRequestRepository customRequestRepository;
    private final DesignVersionRepository designVersionRepository;
    private final DocumentRepository documentRepository;
    private final ImageRepository imageRepository;
    private final DesignRepository designRepository;
    private final DesignSessionPaymentRepository designSessionPaymentRepository;
    private final DesignMetalSpecificationRepository designMetalSpecificationRepository;

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public boolean thereIsNoUnusedDesignSession(Long accountId) {
        return !designSessionRepository.existsByCustomerIdAndStatus(accountId, DesignSessionStatus.UNUSED);
    }

    @Override
    public DesignSessionPayment save(DesignSessionPayment designSessionPayment) {
        updateAuditor(designSessionPayment);
        return designSessionPaymentRepository.save(designSessionPayment);
    }

    @Override
    public Collection<DesignSession> saveAll(Collection<DesignSession> designSessions) {
        designSessions.forEach(this::updateAuditor);
        return designSessionRepository.saveAll(designSessions);
    }

    @Override
    public CustomRequest save(CustomRequest customRequest) {
        updateAuditor(customRequest);
        return customRequestRepository.save(customRequest);
    }

    @Override
    public int getRemainingDesignSessionsCount(Long accountId) {
        return designSessionRepository.countByCustomerIdAndStatus(accountId, DesignSessionStatus.UNUSED);
    }

    @Override
    public List<DesignVersion> getDesignVersionRemainingByDesignId(Long designId, Long designVersionId) {
        return createQuery().select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION)
                .where(Q_DESIGN_VERSION.design.id.eq(designId)
                        .and(Q_DESIGN_VERSION.id.ne(designVersionId)))
                .fetch();
    }

    @Override
    public DesignVersion save(DesignVersion designVersion) {
        updateAuditor(designVersion);
        return designVersionRepository.save(designVersion);
    }

    @Override
    public List<DesignSession> getListDesignSession(Long customerId) {
        return createQuery().select(Q_DESIGN_SESSION)
                .from(Q_DESIGN_SESSION)
                .where(Q_DESIGN_SESSION.customer.id.eq(customerId))
                .fetch();
    }

    @Override
    public Document saveDocument(Document document) {
        updateAuditor(document);
        return documentRepository.save(document);
    }

    @Override
    public Image saveImage(Image image) {
        updateAuditor(image);
        return imageRepository.save(image);
    }

    @Override
    public Optional<Design> getDesignByID(Long designID) {
        return Optional.ofNullable(createQuery().select(Q_DESIGN)
                .from(Q_DESIGN)
                .leftJoin(Q_DESIGN.designVersions, Q_DESIGN_VERSION).fetchJoin()
                .where(Q_DESIGN.id.eq(designID))
                .fetchOne());
    }

    @Override
    public Optional<Account> getCustomerById(Long customerId) {
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(customerId)
                        .and(Q_ACCOUNT.role.eq(Role.CUSTOMER)))
                .fetchOne());
    }

    @Override
    public Optional<Document> getDocumentById(Long documentId) {
        return Optional.ofNullable(createQuery().select(Q_DOCUMENT)
                .from(Q_DOCUMENT)
                .where(Q_DOCUMENT.id.eq(documentId))
                .fetchOne());
    }

    @Override
    public Optional<Image> getImageById(Long imageId) {
        return Optional.ofNullable(createQuery().select(Q_IMAGE)
                .from(Q_IMAGE)
                .where(Q_IMAGE.id.eq(imageId))
                .fetchOne());
    }

    @Override
    public DesignMetalSpecification save(DesignMetalSpecification metalSpecification) {
        updateAuditor(metalSpecification);
        return designMetalSpecificationRepository.save(metalSpecification);
    }

    @Override
    public List<DesignSession> getDesignSessionsByCustomerId(Long customerId) {
        return createQuery()
                .select(Q_DESIGN_SESSION)
                .from(Q_DESIGN_SESSION)
                .where(Q_DESIGN_SESSION.customer.id.eq(customerId)
                        .and(Q_DESIGN_SESSION.status.eq(DesignSessionStatus.UNUSED)))
                .fetch();
    }

    @Override
    public void save(DesignSession designSession) {
        updateAuditor(designSession);
        designSessionRepository.save(designSession);
    }

    @Override
    public Long countDesignVersionNumber(Long designId, Long customerId) {
        return createQuery().select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION)
                .where(Q_DESIGN_VERSION.design.id.eq(designId)
                        .and(Q_DESIGN_VERSION.customer.id.eq(customerId)))
                .distinct().fetchCount();
    }

    @Override
    public Optional<DesignVersion> getDesignVersionById(long designVersionId) {
        return Optional.ofNullable(createQuery().select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION)
                .leftJoin(Q_DESIGN_VERSION.design, Q_DESIGN).fetchJoin()
                .leftJoin(Q_DESIGN.designCustomRequests, Q_DESIGN_CUSTOM_REQUEST).fetchJoin()
                .leftJoin(Q_DESIGN_CUSTOM_REQUEST.customRequest, Q_CUSTOM_REQUEST).fetchJoin()
                .leftJoin(Q_DESIGN_VERSION.customer, Q_ACCOUNT).fetchJoin()
                .where(Q_DESIGN_VERSION.id.eq(designVersionId))
                .fetchOne());
    }

    @Override
    public void updateCustomRequest(CustomRequest customRequest) {
        updateAuditor(customRequest);
        customRequestRepository.save(customRequest);
    }

    @Override
    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public DesignVersions findDesignVersions(ViewDesignVersionsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<DesignVersion> query = createQuery()
                .select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION);
        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();

        if (input.getDesignId() != null) {
            booleanExpressionBuilder.and(Q_DESIGN_VERSION.design.id.eq(input.getDesignId()));
        }

        if (input.getCustomerId() != null) {
            booleanExpressionBuilder.and(Q_DESIGN_VERSION.customer.id.eq(input.getCustomerId()));
        }
        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);

        long count = query.distinct().fetchCount();
        List<DesignVersion> designVersions = query.limit(input.getPageSize()).offset(offset).fetch();
        return DesignVersions.builder()
                .designVersions(designVersions)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public DesignVersion acceptDesignVersion(DesignVersion designVersion) {
        return save(designVersion);
    }

    @Override
    public Optional<Design> getDesign(Long designId) {
        return Optional.ofNullable(createQuery().select(Q_DESIGN)
                .from(Q_DESIGN)
                .where(Q_DESIGN.id.eq(designId))
                .fetchFirst());
    }

    @Override
    public Optional<JewelryCategory> getJewelryCategoryById(Long id) {
        return Optional.ofNullable(createQuery().select(Q_JEWELRY_CATEGORY)
                .from(Q_JEWELRY_CATEGORY)
                .where(Q_JEWELRY_CATEGORY.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Optional<Document> getBlueprintById(Long id) {
        return Optional.ofNullable(createQuery().select(Q_DOCUMENT)
                .from(Q_DOCUMENT)
                .where(Q_DOCUMENT.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Optional<DesignCollection> getDesignCollectionById(Long id) {
        return Optional.ofNullable(createQuery().select(Q_DESIGN_COLLECTION)
                .from(Q_DESIGN_COLLECTION)
                .where(Q_DESIGN_COLLECTION.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Design save(Design design) {
        updateAuditor(design);
        return designRepository.save(design);
    }

    @Override
    public Optional<MetalSpecification> getMetalSpecificationById(Long id) {
        return Optional.ofNullable(createQuery().select(Q_METAL_SPECIFICATION)
                .from(Q_METAL_SPECIFICATION)
                .where(Q_METAL_SPECIFICATION.id.eq(id))
                .fetchFirst());
    }

    @Override
    public Designs getDesigns(ViewDesignsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Design> query = createQuery()
                .select(Q_DESIGN)
                .from(Q_DESIGN)
                .leftJoin(Q_DESIGN.designCollection).fetchJoin()
                .leftJoin(Q_DESIGN.designMetalSpecifications).fetchJoin()
                .leftJoin(Q_DESIGN.jewelryCategory).fetchJoin();
        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();
        booleanExpressionBuilder.and(Q_DESIGN.jewelryCategory.id.isNotNull());
        if (input.getDesignCollectionId() != null) {
            booleanExpressionBuilder.and(Q_DESIGN.designCollection.id.eq(input.getDesignCollectionId()));
        }

        if (input.getSize() != null) {
            booleanExpressionBuilder.and(Q_DESIGN.size.eq(DesignSize.create(input.getSize())));
        }

        if (input.getStatus() != null) {
            switch (input.getStatus()) {
                case AVAILABLE -> booleanExpressionBuilder.and(Q_DESIGN.status.eq(DesignStatus.AVAILABLE));
                case UNAVAILABLE -> booleanExpressionBuilder.and(Q_DESIGN.status.eq(DesignStatus.UNAVAILABLE));
                case USED -> booleanExpressionBuilder.and(Q_DESIGN.status.eq(DesignStatus.USED));
            }
        }

        if (input.getMetalSpecId() != null) {
            booleanExpressionBuilder.and(Q_DESIGN.designMetalSpecifications.any().id.eq(input.getMetalSpecId()));
        }

        if (input.getCharacteristic() != null) {
            switch (input.getCharacteristic()) {
                case ANDROGYNOUS -> booleanExpressionBuilder.and(Q_DESIGN.characteristic.eq(DesignCharacteristic.ANDROGYNOUS));
                case FEMININE -> booleanExpressionBuilder.and(Q_DESIGN.characteristic.eq(DesignCharacteristic.FEMININE));
                case MASCULINE -> booleanExpressionBuilder.and(Q_DESIGN.characteristic.eq(DesignCharacteristic.MASCULINE));
            }
        }

        if (input.getCategoryId() != null) {
            booleanExpressionBuilder.and(Q_DESIGN.jewelryCategory.id.eq(input.getCategoryId()));
        }

        if (input.getName() != null) {
            booleanExpressionBuilder.and(Q_DESIGN.name.toLowerCase().contains(input.getName().toLowerCase()));
        }
        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);

        long count = query.distinct().fetchCount();
        List<Design> designs = query.limit(input.getPageSize()).offset(offset).fetch();
        return Designs.builder()
                .designs(designs)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public DesignCollections getDesignCollections(ViewDesignCollectionsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<DesignCollection> query = createQuery()
                .select(Q_DESIGN_COLLECTION)
                .from(Q_DESIGN_COLLECTION);

        long count = query.distinct().fetchCount();
        List<DesignCollection> designCollections = query.limit(input.getPageSize()).offset(offset).fetch();
        return DesignCollections.builder()
                .designCollections(designCollections)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
