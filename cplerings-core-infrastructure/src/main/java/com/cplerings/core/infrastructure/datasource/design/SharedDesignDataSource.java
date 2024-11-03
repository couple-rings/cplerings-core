package com.cplerings.core.infrastructure.datasource.design;

import com.cplerings.core.application.design.datasource.CheckRemainingDesignSessionDataSource;
import com.cplerings.core.application.design.datasource.CreateDesignSessionDataSource;
import com.cplerings.core.application.design.datasource.CreateDesignVersionDataSource;
import com.cplerings.core.application.design.datasource.ProcessDesignSessionPaymentDataSource;
import com.cplerings.core.application.design.datasource.UpdateDesignVersionStatusDataSource;
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
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
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
import com.querydsl.jpa.impl.JPAUpdateClause;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@DataSource
@RequiredArgsConstructor
public class SharedDesignDataSource extends AbstractDataSource
        implements CreateDesignSessionDataSource, ProcessDesignSessionPaymentDataSource, CheckRemainingDesignSessionDataSource,
        CreateDesignVersionDataSource, ViewDesignVersionDataSource, ViewDesignVersionsDataSource, UpdateDesignVersionStatusDataSource {

    private static final QDesign Q_DESIGN = QDesign.design;
    private static final QDesignVersion Q_DESIGN_VERSION = QDesignVersion.designVersion;
    private static final QAccount Q_ACCOUNT = QAccount.account;

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
    public DesignVersion save(DesignVersion designVersion) {
        updateAuditor(designVersion);
        return designVersionRepository.save(designVersion);
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
    public Optional<Design> getDesignByID(long designID) {
        return Optional.ofNullable(createQuery().select(Q_DESIGN)
                .from(Q_DESIGN)
                .leftJoin(Q_DESIGN.designVersions, Q_DESIGN_VERSION).fetchJoin()
                .where(Q_DESIGN.id.eq(designID))
                .fetchOne());
    }

    @Override
    public Optional<Account> getCustomerById(long customerId) {
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(customerId)
                        .and(Q_ACCOUNT.role.eq(Role.CUSTOMER)))
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
    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public DesignVersions findDesignVersionsByCustomerId(Long customerId, ViewDesignVersionsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<DesignVersion> query = createQuery()
                .select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION);
        if (input.getDesignId() != null) {
            query = query.where(Q_DESIGN_VERSION.customer.id.eq(customerId)
                    .and(Q_DESIGN_VERSION.design.id.eq(input.getDesignId())));
        } else {
            query = query.where(Q_DESIGN_VERSION.customer.id.eq(customerId));
        }
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
    public DesignVersions findDesignVersions(ViewDesignVersionsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<DesignVersion> query = createQuery()
                .select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION);
        if (input.getDesignId() != null) {
            query = query.where(Q_DESIGN_VERSION.design.id.eq(input.getDesignId()));
        }
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
