package com.cplerings.core.infrastructure.datasource.design.request;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.design.datasource.CreateCustomRequestDataSource;
import com.cplerings.core.application.design.datasource.ViewCustomRequestDataSource;
import com.cplerings.core.application.design.datasource.ViewCustomRequestsDataSource;
import com.cplerings.core.application.design.datasource.result.CustomRequests;
import com.cplerings.core.application.design.input.ViewCustomRequestsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignStatus;
import com.cplerings.core.domain.design.QDesign;
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
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DataSource
public class SharedCustomRequestDataSource extends AbstractDataSource implements ViewCustomRequestDataSource, CreateCustomRequestDataSource, ViewCustomRequestsDataSource {

    private static final QCustomRequest Q_CUSTOM_REQUEST = QCustomRequest.customRequest;
    private static final QDesign Q_DESIGN = QDesign.design;
    private static final QDesignCustomRequest Q_DESIGN_CUSTOM_REQUEST = QDesignCustomRequest.designCustomRequest;

    private final DesignRepository designRepository;
    private final AccountRepository accountRepository;
    private final CustomRequestRepository customRequestRepository;
    private final DesignCustomRequestRepository designCustomRequestRepository;

    @Override
    public Optional<CustomRequest> getCustomRequestById(Long customRequestId) {
        return Optional.ofNullable(createQuery().select(Q_CUSTOM_REQUEST)
                .from(Q_CUSTOM_REQUEST)
                .where(Q_CUSTOM_REQUEST.id.eq(customRequestId))
                .fetchOne());
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
            }
        }
        if (input.getCustomerId() != null) {
            booleanExpressionBuilder.and(Q_CUSTOM_REQUEST.customer.id.eq(input.getCustomerId()));
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
}