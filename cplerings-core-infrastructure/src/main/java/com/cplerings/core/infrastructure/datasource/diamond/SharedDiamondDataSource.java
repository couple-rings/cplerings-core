package com.cplerings.core.infrastructure.datasource.diamond;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.diamond.datasource.CreateDiamondDataSource;
import com.cplerings.core.application.diamond.datasource.ViewDiamondSpecificationDataSource;
import com.cplerings.core.application.diamond.datasource.ViewDiamondsDataSource;
import com.cplerings.core.application.diamond.datasource.result.DiamondSpecifications;
import com.cplerings.core.application.diamond.datasource.result.Diamonds;
import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;
import com.cplerings.core.application.diamond.input.ViewDiamondsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.diamond.QDiamond;
import com.cplerings.core.domain.diamond.QDiamondSpecification;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.DiamondRepository;
import com.cplerings.core.infrastructure.repository.DiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedDiamondDataSource extends AbstractDataSource
        implements ViewDiamondSpecificationDataSource, CreateDiamondDataSource, ViewDiamondsDataSource {

    private static final QDiamondSpecification Q_DIAMOND_SPECIFICATION = QDiamondSpecification.diamondSpecification;
    private static final QDiamond Q_DIAMOND = QDiamond.diamond;

    private final DocumentRepository documentRepository;
    private final DiamondSpecificationRepository diamondSpecificationRepository;
    private final BranchRepository branchRepository;
    private final DiamondRepository diamondRepository;

    @Override
    public DiamondSpecifications getDiamondSpecifications(ViewDiamondSpecificationInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<DiamondSpecification> query = createQuery()
                .select(Q_DIAMOND_SPECIFICATION)
                .from(Q_DIAMOND_SPECIFICATION);
        long count = query.distinct().fetchCount();
        List<DiamondSpecification> diamondSpecifications = query.limit(input.getPageSize()).offset(offset).fetch();
        return DiamondSpecifications.builder()
                .diamondSpecifications(diamondSpecifications)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    @Override
    public Optional<Document> findGIADocumentById(Long giaDocumentId) {
        return documentRepository.findById(giaDocumentId);
    }

    @Override
    public Optional<DiamondSpecification> findDiamondSpecificationById(Long diamondSpecificationId) {
        return diamondSpecificationRepository.findById(diamondSpecificationId);
    }

    @Override
    public Optional<Branch> findBranchById(Long branchId) {
        return branchRepository.findById(branchId);
    }

    @Override
    public Diamond save(Diamond diamond) {
        updateAuditor(diamond);
        return diamondRepository.save(diamond);
    }

    @Override
    public Diamonds getDiamonds(ViewDiamondsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Diamond> query = createQuery()
                .select(Q_DIAMOND)
                .from(Q_DIAMOND);

        BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();

        if (input.getBranchId() != null) {
            booleanExpressionBuilder.and(Q_DIAMOND.branch.id.eq(input.getBranchId()));
        }

        if (input.getGiaReportNumber() != null) {
            booleanExpressionBuilder.and(Q_DIAMOND.giaReportNumber.eq(input.getGiaReportNumber()));
        }

        final BooleanExpression predicate = booleanExpressionBuilder.build();
        query.where(predicate);

        long count = query.distinct().fetchCount();
        List<Diamond> diamonds = query.limit(input.getPageSize()).offset(offset).fetch();
        return Diamonds.builder()
                .diamonds(diamonds)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
