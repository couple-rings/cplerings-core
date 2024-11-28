package com.cplerings.core.infrastructure.datasource.design.request;

import com.cplerings.core.application.design.datasource.CancelCustomRequestDataSource;
import com.cplerings.core.application.design.datasource.CreateCustomRequestDataSource;
import com.cplerings.core.application.design.datasource.DetermineCustomRequestDataSource;
import com.cplerings.core.application.design.datasource.ViewCustomRequestDataSource;
import com.cplerings.core.application.design.datasource.ViewCustomRequestsDataSource;
import com.cplerings.core.application.design.datasource.result.CustomRequests;
import com.cplerings.core.application.design.input.ViewCustomRequestsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignStatus;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.QDesign;
import com.cplerings.core.domain.design.QDesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.design.request.DesignCustomRequest;
import com.cplerings.core.domain.design.request.QCustomRequest;
import com.cplerings.core.domain.design.request.QDesignCustomRequest;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.CustomRequestRepository;
import com.cplerings.core.infrastructure.repository.DesignCustomRequestRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;

import lombok.RequiredArgsConstructor;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@DataSource
public class SharedCustomRequestDataSource extends AbstractDataSource implements ViewCustomRequestDataSource, CreateCustomRequestDataSource, ViewCustomRequestsDataSource, DetermineCustomRequestDataSource, CancelCustomRequestDataSource {

    private static final QCustomRequest Q_CUSTOM_REQUEST = QCustomRequest.customRequest;
    private static final QDesign Q_DESIGN = QDesign.design;
    private static final QDesignCustomRequest Q_DESIGN_CUSTOM_REQUEST = QDesignCustomRequest.designCustomRequest;
    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QDesignVersion Q_DESIGN_VERSION = QDesignVersion.designVersion;

    private final DesignRepository designRepository;
    private final AccountRepository accountRepository;
    private final CustomRequestRepository customRequestRepository;
    private final DesignCustomRequestRepository designCustomRequestRepository;
    private final DesignVersionRepository designVersionRepository;

    @Override
    public Optional<CustomRequest> getCustomRequestById(Long customRequestId) {
        return Optional.ofNullable(createQuery().select(Q_CUSTOM_REQUEST)
                .from(Q_CUSTOM_REQUEST)
                .leftJoin(Q_CUSTOM_REQUEST.designCustomRequests, Q_DESIGN_CUSTOM_REQUEST).fetchJoin()
                .leftJoin(Q_DESIGN_CUSTOM_REQUEST.design, Q_DESIGN).fetchJoin()
                .where(Q_CUSTOM_REQUEST.id.eq(customRequestId))
                .fetchOne());
    }

    @Override
    public List<DesignVersion> getDesignVersionsByDesignId(Long designId) {
        return createQuery()
                .select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION)
                .where(Q_DESIGN_VERSION.design.id.eq(designId))
                .fetch();
    }

    @Override
    public Design save(Design design) {
        updateAuditor(design);
        return designRepository.save(design);
    }

    @Override
    public DesignVersion save(DesignVersion designVersion) {
        updateAuditor(designVersion);
        return designVersionRepository.save(designVersion);
    }

    @Override
    public Collection<Design> getAvailableDesignsByIds(Collection<Long> designIds) {
        return new HashSet<>(createQuery().select(Q_DESIGN)
                .from(Q_DESIGN)
                .where(Q_DESIGN.id.in(designIds).and(Q_DESIGN.status.eq(DesignStatus.AVAILABLE)))
                .fetch());
    }

    @Override
    public Collection<Design> saveDesigns(Collection<Design> designs) {
        designs.forEach(this::updateAuditor);
        return designRepository.saveAll(designs);
    }

    @Override
    public Optional<Account> getCustomerById(Long customerId) {
        return accountRepository.findById(customerId);
    }

    @Override
    public CustomRequest save(CustomRequest customRequest) {
        updateAuditor(customRequest);
        return customRequestRepository.save(customRequest);
    }

    @Override
    public Collection<DesignCustomRequest> saveDesignCustomRequests(Collection<DesignCustomRequest> designCustomRequests) {
        designCustomRequests.forEach(this::updateAuditor);
        return designCustomRequestRepository.saveAll(designCustomRequests);
    }

    @Override
    public Collection<DesignVersion> getAllDesignVersionsOfPreviousDesignSessions(Long customerId, Collection<Long> designIds) {
        return new HashSet<>(createQuery().select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION)
                .where(Q_DESIGN_VERSION.customer.id.eq(customerId)
                        .and(Q_DESIGN_VERSION.design.id.in(designIds)))
                .fetch());
    }

    @Override
    public Collection<DesignVersion> saveAll(Collection<DesignVersion> oldDesignVersions) {
        oldDesignVersions.forEach(this::updateAuditor);
        return designVersionRepository.saveAll(oldDesignVersions);
    }

    @Override
    public CustomRequests getCustomRequests(ViewCustomRequestsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<CustomRequest> query = createQuery()
                .select(Q_CUSTOM_REQUEST)
                .from(Q_CUSTOM_REQUEST)
                .leftJoin(Q_CUSTOM_REQUEST.designCustomRequests, Q_DESIGN_CUSTOM_REQUEST).fetchJoin()
                .leftJoin(Q_DESIGN_CUSTOM_REQUEST.design, Q_DESIGN).fetchJoin();
        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();
        if (input.getStatus() != null) {
            switch (input.getStatus()) {
                case PENDING -> booleanExpressionBuilder.and(Q_CUSTOM_REQUEST.status.eq(CustomRequestStatus.PENDING));
                case APPROVED -> booleanExpressionBuilder.and(Q_CUSTOM_REQUEST.status.eq(CustomRequestStatus.APPROVED));
                case REJECTED -> booleanExpressionBuilder.and(Q_CUSTOM_REQUEST.status.eq(CustomRequestStatus.REJECTED));
                case COMPLETED -> booleanExpressionBuilder.and(Q_CUSTOM_REQUEST.status.eq(CustomRequestStatus.COMPLETED));
            }
        }
        if (input.getCustomerId() != null) {
            booleanExpressionBuilder.and(Q_CUSTOM_REQUEST.customer.id.eq(input.getCustomerId()));
        }
        if (input.getStaffId() != null) {
            booleanExpressionBuilder.and(Q_CUSTOM_REQUEST.staff.id.eq(input.getStaffId()));
        }
        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);

        long count = query.distinct().fetchCount();
        List<CustomRequest> customRequests = query.limit(input.getPageSize()).offset(offset).fetch();
        return CustomRequests.builder()
                .customRequests(customRequests)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Optional<CustomRequest> getCraftingRequestById(Long id) {
        return Optional.ofNullable(createQuery().select(Q_CUSTOM_REQUEST)
                .from(Q_CUSTOM_REQUEST)
                .where(Q_CUSTOM_REQUEST.id.eq(id))
                .fetchOne());
    }

    @Override
    public CustomRequest updateCraftingRequest(CustomRequest customRequest) {
        updateAuditor(customRequest);
        return customRequestRepository.save(customRequest);
    }

    @Override
    public Optional<Account> getStaff(Long staffId) {
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(staffId))
                .fetchOne());
    }
}
