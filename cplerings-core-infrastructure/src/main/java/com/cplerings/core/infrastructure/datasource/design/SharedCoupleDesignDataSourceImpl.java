package com.cplerings.core.infrastructure.datasource.design;

import com.cplerings.core.application.design.datasource.ViewCoupleDesignDataSource;
import com.cplerings.core.application.design.datasource.result.DesignCouples;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.design.DesignCouple;
import com.cplerings.core.domain.design.QDesign;
import com.cplerings.core.domain.design.QDesignCollection;
import com.cplerings.core.domain.design.QDesignCouple;
import com.cplerings.core.domain.design.QDesignDiamondSpecification;
import com.cplerings.core.domain.design.QDesignMetalSpecification;
import com.cplerings.core.domain.diamond.QDiamondSpecification;
import com.cplerings.core.domain.file.QDocument;
import com.cplerings.core.domain.file.QImage;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

import lombok.RequiredArgsConstructor;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

import java.math.BigDecimal;
import java.util.List;

@DataSource
@RequiredArgsConstructor
public class SharedCoupleDesignDataSourceImpl extends AbstractDataSource
        implements ViewCoupleDesignDataSource {

    private static final QDesignCouple Q_DESIGN_COUPLE = QDesignCouple.designCouple;
    private static final QImage Q_IMAGE = QImage.image;
    private static final QDesign Q_DESIGN = QDesign.design;
    private static final QDesignMetalSpecification Q_DESIGN_METAL_SPECIFICATION = QDesignMetalSpecification.designMetalSpecification;
    private static final QDesignCollection Q_DESIGN_COLLECTION = QDesignCollection.designCollection;
    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;

    private static final QDiamondSpecification Q_DIAMOND_SPECIFICATION = QDiamondSpecification.diamondSpecification;
    private static final QDesignDiamondSpecification Q_DESIGN_DIAMOND_SPECIFICATION = QDesignDiamondSpecification.designDiamondSpecification;

    private static final QDocument Q_DOCUMENT = QDocument.document;

    @Override
    public DesignCouples getDesignCouples(ViewCoupleDesignInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<DesignCouple> query = testQuery(input);
        long count = query.distinct().fetchCount();
        List<DesignCouple> designCouples = testQuery(input).limit(input.getPageSize()).offset(offset).fetch();
        return DesignCouples.builder()
                .designCouples(designCouples)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }

    private BlazeJPAQuery<DesignCouple> testQuery(ViewCoupleDesignInput input) {
        BlazeJPAQuery<DesignCouple> query = createQuery().select(Q_DESIGN_COUPLE)
                .from(Q_DESIGN_COUPLE)
                .leftJoin(Q_DESIGN_COUPLE.previewImage, Q_IMAGE).fetchJoin()
                .leftJoin(Q_DESIGN_COUPLE.designs, Q_DESIGN).fetchJoin()
                .leftJoin(Q_DESIGN.blueprint, Q_DOCUMENT).fetchJoin()
                .leftJoin(Q_DESIGN.designCollection, Q_DESIGN_COLLECTION).fetchJoin()
                .leftJoin(Q_DESIGN.designMetalSpecifications, Q_DESIGN_METAL_SPECIFICATION).fetchJoin()
                .leftJoin(Q_DESIGN_METAL_SPECIFICATION.metalSpecification, Q_METAL_SPECIFICATION).fetchJoin()
                .leftJoin(Q_DESIGN.designDiamondSpecifications, Q_DESIGN_DIAMOND_SPECIFICATION).fetchJoin()
                .leftJoin(Q_DESIGN_DIAMOND_SPECIFICATION.diamondSpecification, Q_DIAMOND_SPECIFICATION).fetchJoin();

        final BooleanExpressionBuilder booleanExpressionBuilder = createBooleanExpressionBuilder();
        if (input.getMetalSpecificationId() != null) {
            booleanExpressionBuilder.and(Q_DESIGN_COUPLE.designs
                    .any().designMetalSpecifications
                    .any().metalSpecification.id.eq(input.getMetalSpecificationId()));
        }
        if (input.getCollectionId() != null) {
            booleanExpressionBuilder.and(Q_DESIGN_COUPLE.designs
                    .any().designCollection.id.eq(input.getCollectionId()));
        }
        if (NumberUtils.isLessThanOrEqual(input.getMinPrice(), BigDecimal.ZERO)) {
            if (NumberUtils.isLessThanOrEqual(input.getMaxPrice(), BigDecimal.ZERO)) {
                // Get all results
                final BooleanExpression predicate = booleanExpressionBuilder.build();
                if (predicate != null) {
                    query.where(predicate);
                }
                return query;
            } else {
                // Min = 0, max is defined
                booleanExpressionBuilder.and((Q_DESIGN.metalWeight.weight
                        .multiply(3.75).multiply(2).multiply(getMinMetalPriceQuery(Q_DESIGN.id))
                        .add(getMinDiamondPriceQuery(Q_DESIGN.id)))
                        .multiply(1.3)
                        .loe(input.getMaxPrice()));
            }
        } else {
            if (NumberUtils.isLessThanOrEqual(input.getMaxPrice(), BigDecimal.ZERO)) {
                // Max = infinity, min is defined
                booleanExpressionBuilder.and((Q_DESIGN.metalWeight.weight
                        .multiply(3.75).multiply(2).multiply(getMinMetalPriceQuery(Q_DESIGN.id))
                        .add(getMinDiamondPriceQuery(Q_DESIGN.id)))
                        .multiply(1.3)
                        .goe(input.getMinPrice()));
            } else {
                // Between min and max
                booleanExpressionBuilder.and((Q_DESIGN.metalWeight.weight
                        .multiply(3.75).multiply(2).multiply(getMinMetalPriceQuery(Q_DESIGN.id))
                        .add(getMinDiamondPriceQuery(Q_DESIGN.id)))
                        .multiply(1.3)
                        .between(input.getMinPrice(), input.getMaxPrice()));
            }
        }
        final BooleanExpression predicate = booleanExpressionBuilder.build();
        if (predicate != null) {
            query.where(predicate);
        }
        return query;
    }

    private JPQLQuery<BigDecimal> getMinMetalPriceQuery(NumberPath<Long> designId) {
        final QDesignMetalSpecification qDesignMetalSpecification = new QDesignMetalSpecification("qDesignMetalSpecificationSubQuery");
        return JPAExpressions.select(qDesignMetalSpecification.metalSpecification.pricePerUnit.amount.min())
                .from(qDesignMetalSpecification)
                .where(qDesignMetalSpecification.design.id.eq(designId));
    }

    private JPQLQuery<BigDecimal> getMinDiamondPriceQuery(NumberPath<Long> designId) {
        final QDesignDiamondSpecification qDesignDiamondSpecification = new QDesignDiamondSpecification("qDesignMetalSpecificationSubQuery");
        return JPAExpressions.select(qDesignDiamondSpecification.diamondSpecification.price.amount.min().multiply(BigDecimal.valueOf(1.3)))
                .from(qDesignDiamondSpecification)
                .where(qDesignDiamondSpecification.design.id.eq(designId));
    }
}
