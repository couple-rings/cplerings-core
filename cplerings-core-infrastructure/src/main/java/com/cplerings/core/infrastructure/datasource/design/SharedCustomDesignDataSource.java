package com.cplerings.core.infrastructure.datasource.design;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.design.datasource.CreateCustomDesignDataSource;
import com.cplerings.core.application.design.datasource.ViewCustomDesignsDataSource;
import com.cplerings.core.application.design.datasource.result.CustomDesigns;
import com.cplerings.core.application.design.input.ViewCustomDesignsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.application.design.datasource.ViewCustomDesignDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.CustomDesignDiamondSpecification;
import com.cplerings.core.domain.design.CustomDesignMetalSpecification;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.QCustomDesign;
import com.cplerings.core.domain.design.QDesignVersion;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.diamond.QDiamondSpecification;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.QDocument;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.spouse.QSpouse;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.CustomDesignDiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.CustomDesignMetalSpecificationRepository;
import com.cplerings.core.infrastructure.repository.CustomDesignRepository;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedCustomDesignDataSource extends AbstractDataSource implements CreateCustomDesignDataSource, ViewCustomDesignsDataSource, ViewCustomDesignDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;
    private static final QSpouse Q_SPOUSE = QSpouse.spouse;
    private static final QDesignVersion Q_DESIGN_VERSION = QDesignVersion.designVersion;
    private static final QCustomDesign Q_CUSTOM_DESIGN = QCustomDesign.customDesign;
    private static final QDocument Q_DOCUMENT = QDocument.document;
    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;
    private static final QDiamondSpecification Q_DIAMOND_SPECIFICATION = QDiamondSpecification.diamondSpecification;

    private final CustomDesignRepository customDesignRepository;
    private final CustomDesignMetalSpecificationRepository customDesignMetalSpecificationRepository;
    private final CustomDesignDiamondSpecificationRepository customDesignDiamondSpecificationRepository;

    @Override
    public CustomDesign save(CustomDesign customDesign) {
        updateAuditor(customDesign);
        return customDesignRepository.save(customDesign);
    }

    @Override
    public Optional<Account> getCustomerById(Long customerId) {
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.id.eq(customerId))
                .fetchOne());
    }

    @Override
    public Optional<Spouse> getSpouseById(Long spouseId) {
        return Optional.ofNullable(createQuery().select(Q_SPOUSE)
                .from(Q_SPOUSE)
                .where(Q_SPOUSE.id.eq(spouseId))
                .fetchOne());
    }

    @Override
    public Optional<DesignVersion> getDesignVersionById(Long designVersionId) {
        return Optional.ofNullable(createQuery().select(Q_DESIGN_VERSION)
                .from(Q_DESIGN_VERSION)
                .where(Q_DESIGN_VERSION.id.eq(designVersionId))
                .fetchOne());
    }

    @Override
    public Optional<CustomDesign> getCustomDesignBySpouseId(Long spouseId) {
        return Optional.ofNullable(createQuery().select(Q_CUSTOM_DESIGN)
                .from(Q_CUSTOM_DESIGN)
                .where(Q_CUSTOM_DESIGN.spouse.id.eq(spouseId)
                        .and(Q_CUSTOM_DESIGN.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public Optional<CustomDesign> getCustomDesignByDesignVersionId(Long designVersionId) {
        return Optional.ofNullable(createQuery().select(Q_CUSTOM_DESIGN)
                .from(Q_CUSTOM_DESIGN)
                .where(Q_CUSTOM_DESIGN.designVersion.id.eq(designVersionId)
                        .and(Q_CUSTOM_DESIGN.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public Optional<Document> getDocumentById(Long id) {
        return Optional.ofNullable(createQuery().select(Q_DOCUMENT)
                .from(Q_DOCUMENT)
                .where(Q_DOCUMENT.id.eq(id)
                        .and(Q_DOCUMENT.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public List<MetalSpecification> getMetalSpecById(List<Long> ids) {
        return createQuery().select(Q_METAL_SPECIFICATION)
                .from(Q_METAL_SPECIFICATION)
                .where(Q_METAL_SPECIFICATION.id.in(ids)
                        .and(Q_METAL_SPECIFICATION.state.eq(State.ACTIVE)))
                .fetch();
    }

    @Override
    public List<DiamondSpecification> getDiamondSpecById(List<Long> ids) {
        return createQuery().select(Q_DIAMOND_SPECIFICATION)
                .from(Q_DIAMOND_SPECIFICATION)
                .where(Q_DIAMOND_SPECIFICATION.id.in(ids)
                        .and(Q_DIAMOND_SPECIFICATION.state.eq(State.ACTIVE)))
                .fetch();
    }

    @Override
    public List<CustomDesignMetalSpecification> saveMetalSpec(List<CustomDesignMetalSpecification> customDesignMetalSpecifications) {
        customDesignMetalSpecifications.forEach(this::updateAuditor);
        return customDesignMetalSpecificationRepository.saveAll(customDesignMetalSpecifications);
    }

    @Override
    public List<CustomDesignDiamondSpecification> saveDiamondSpec(List<CustomDesignDiamondSpecification> customDesignDiamondSpecifications) {
        customDesignDiamondSpecifications.forEach(this::updateAuditor);
        return customDesignDiamondSpecificationRepository.saveAll(customDesignDiamondSpecifications);
    }

    @Override
    public CustomDesigns getCustomDesigns(Long customerId, ViewCustomDesignsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<CustomDesign> query = createQuery()
                .select(Q_CUSTOM_DESIGN)
                .from(Q_CUSTOM_DESIGN);
        if (customerId != null) {
            query.where(Q_CUSTOM_DESIGN.id.eq(customerId));
        }
        long count = query.distinct().fetchCount();
        List<CustomDesign> customDesigns = query.limit(input.getPageSize()).offset(offset).fetch();
        return CustomDesigns.builder()
                .customDesigns(customDesigns)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Optional<Account> findAccountByEmail( String email) {
        return Optional.ofNullable(createQuery().select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.email.eq(email)
                        .and(Q_ACCOUNT.state.eq(State.ACTIVE)))
                .fetchOne());
    }

    @Override
    public Optional<CustomDesign> getCustomDesignByCustomDesignId(Long customDesignId) {
        return Optional.ofNullable(createQuery().select(Q_CUSTOM_DESIGN)
                .from(Q_CUSTOM_DESIGN)
                .where(Q_CUSTOM_DESIGN.id.eq(customDesignId)
                        .and(Q_CUSTOM_DESIGN.state.eq(State.ACTIVE)))
                .fetchOne());
    }
}
