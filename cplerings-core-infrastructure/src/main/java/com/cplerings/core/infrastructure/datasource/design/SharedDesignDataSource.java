package com.cplerings.core.infrastructure.datasource.design;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.design.datasource.CheckRemainingDesignSessionDataSource;
import com.cplerings.core.application.design.datasource.CreateDesignSessionDataSource;
import com.cplerings.core.application.design.datasource.CreateDesignVersionDataSource;
import com.cplerings.core.application.design.datasource.DetermineDesignVersionDataSource;
import com.cplerings.core.application.design.datasource.ProcessDesignSessionPaymentDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignSessionsLeftDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignVersionDataSource;
import com.cplerings.core.application.design.datasource.ViewDesignVersionsDataSource;
import com.cplerings.core.application.design.datasource.result.DesignVersions;
import com.cplerings.core.application.design.input.ViewDesignVersionsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.QDesign;
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
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.CustomRequestRepository;
import com.cplerings.core.infrastructure.repository.DesignSessionRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.infrastructure.repository.PaymentReceiverRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedDesignDataSource extends AbstractDataSource
        implements CreateDesignSessionDataSource, ProcessDesignSessionPaymentDataSource, CheckRemainingDesignSessionDataSource,
        CreateDesignVersionDataSource, ViewDesignVersionDataSource, ViewDesignVersionsDataSource, DetermineDesignVersionDataSource, ViewDesignSessionsLeftDataSource {

    private static final QDesign Q_DESIGN = QDesign.design;
    private static final QDesignVersion Q_DESIGN_VERSION = QDesignVersion.designVersion;
    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QCustomRequest Q_CUSTOM_REQUEST = QCustomRequest.customRequest;
    private static final QDesignCustomRequest Q_DESIGN_CUSTOM_REQUEST = QDesignCustomRequest.designCustomRequest;
    private static final QDocument Q_DOCUMENT = QDocument.document;
    private static final QImage Q_IMAGE = QImage.image;
    private static final QDesignSession Q_DESIGN_SESSION = QDesignSession.designSession;

    private final DesignSessionRepository designSessionRepository;
    private final AccountRepository accountRepository;
    private final PaymentReceiverRepository paymentReceiverRepository;
    private final CustomRequestRepository customRequestRepository;
    private final DesignVersionRepository designVersionRepository;
    private final DocumentRepository documentRepository;
    private final ImageRepository imageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public boolean thereIsNoUnusedDesignSession(Long accountId) {
        return !designSessionRepository.existsByCustomerIdAndStatus(accountId, DesignSessionStatus.UNUSED);
    }

    @Override
    public PaymentReceiver save(PaymentReceiver paymentReceiver) {
        updateAuditor(paymentReceiver);
        return paymentReceiverRepository.save(paymentReceiver);
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
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
        updateAuditor(designVersion);
        return designVersionRepository.save(designVersion);
    }
}
