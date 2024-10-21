package com.cplerings.core.infrastructure.datasource.design;

import java.math.BigDecimal;
import java.util.List;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.design.datasource.ViewCoupleDesignDataSource;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.application.design.queryoutput.DesignCouples;
import com.cplerings.core.domain.design.DesignCouple;
import com.cplerings.core.domain.design.QDesign;
import com.cplerings.core.domain.design.QDesignCollection;
import com.cplerings.core.domain.design.QDesignCouple;
import com.cplerings.core.domain.design.QDesignCoupleDesign;
import com.cplerings.core.domain.design.QDesignMetalSpecification;
import com.cplerings.core.domain.file.QImage;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedCoupleDesignDataSourceImpl extends AbstractDataSource
        implements ViewCoupleDesignDataSource {

    private static final QDesignCouple Q_DESIGN_COUPLE = QDesignCouple.designCouple;
    private static final QImage Q_IMAGE = QImage.image;
    private static final QDesignCoupleDesign Q_DESIGN_COUPLE_DESIGN = QDesignCoupleDesign.designCoupleDesign;
    private static final QDesign Q_DESIGN = QDesign.design;
    private static final QDesignMetalSpecification Q_DESIGN_METAL_SPECIFICATION = QDesignMetalSpecification.designMetalSpecification;
    private static final QDesignCollection Q_DESIGN_COLLECTION = QDesignCollection.designCollection;
    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;

    @Override
    public DesignCouples getDesignCouples(ViewCoupleDesignInput viewCoupleDesignInput) {
        var offset = (viewCoupleDesignInput.getPage() - 1) * viewCoupleDesignInput.getPageSize();
        BlazeJPAQuery<DesignCouple> query = null;
        // No filter
        if (viewCoupleDesignInput.getCollectionId() == null && viewCoupleDesignInput.getMetalSpecificationId() == null && viewCoupleDesignInput.getMaxPrice() == null && viewCoupleDesignInput.getMinPrice() == null) {
            query = paginatedWithoutAnyFilter();
        }
        // Has filter without price
        else if (viewCoupleDesignInput.getMaxPrice() == null && viewCoupleDesignInput.getMinPrice() == null) {
            query = paginatedWithFilterWithoutPrice(viewCoupleDesignInput);
        }
        // Has filter with all fields
        else {
            query = paginatedWithPriceFilter(viewCoupleDesignInput);
        }

        long count = query.fetchCount();
        int totalPages = (int) Math.ceil((double) count / viewCoupleDesignInput.getPageSize());
        List<DesignCouple> designCouples = query.limit(viewCoupleDesignInput.getPageSize()).offset(offset).fetch();
        DesignCouples designCouplesReturn = new DesignCouples(designCouples, count, viewCoupleDesignInput.getPage(), viewCoupleDesignInput.getPageSize(), totalPages);
        return designCouplesReturn;
    }

    private BlazeJPAQuery<DesignCouple> paginatedWithoutAnyFilter() {
        return createQuery().select(Q_DESIGN_COUPLE)
                .from(Q_DESIGN_COUPLE)
                .leftJoin(Q_DESIGN_COUPLE.previewImage, Q_IMAGE).fetchJoin();
    }

    private BlazeJPAQuery<DesignCouple> paginatedWithFilterWithoutPrice(ViewCoupleDesignInput viewCoupleDesignInput) {
        BlazeJPAQuery<DesignCouple> query = createQuery().select(Q_DESIGN_COUPLE)
                .from(Q_DESIGN_COUPLE)
                .leftJoin(Q_DESIGN_COUPLE.previewImage, Q_IMAGE).fetchJoin()
                .leftJoin(Q_DESIGN_COUPLE_DESIGN)
                    .on(Q_DESIGN_COUPLE_DESIGN.designCouple.id.eq(Q_DESIGN_COUPLE.id))
                .leftJoin(Q_DESIGN)
                    .on(Q_DESIGN.id.eq(Q_DESIGN_COUPLE_DESIGN.design.id))
                .leftJoin(Q_DESIGN_METAL_SPECIFICATION)
                    .on(Q_DESIGN_COUPLE_DESIGN.design.id.eq(Q_DESIGN_METAL_SPECIFICATION.design.id))
                .leftJoin(Q_DESIGN_COLLECTION)
                    .on(Q_DESIGN_COLLECTION.id.eq(Q_DESIGN.designCollection.id));

            query.where(Q_DESIGN_METAL_SPECIFICATION.metalSpecification.id.eq(viewCoupleDesignInput.getMetalSpecificationId())
                    .and(Q_DESIGN_COLLECTION.id.eq(viewCoupleDesignInput.getCollectionId())));

        return query;
    }

    private BlazeJPAQuery<DesignCouple> paginatedWithPriceFilter(ViewCoupleDesignInput viewCoupleDesignInput) {
        BigDecimal getMinMetalPriceQuery = createQuery()
                .select(Q_METAL_SPECIFICATION.pricePerUnit.amount.min())
                .from(Q_METAL_SPECIFICATION)
                .fetchOne();

        BigDecimal getMinDiamondPriceQuery = createQuery()
                .select(Q_METAL_SPECIFICATION.pricePerUnit.amount.min())
                .from(Q_METAL_SPECIFICATION)
                .fetchOne();

        BlazeJPAQuery<DesignCouple> query = createQuery().select(Q_DESIGN_COUPLE)
                .from(Q_DESIGN_COUPLE)
                .leftJoin(Q_DESIGN_COUPLE.previewImage, Q_IMAGE).fetchJoin()
                .leftJoin(Q_DESIGN_COUPLE_DESIGN)
                    .on(Q_DESIGN_COUPLE_DESIGN.designCouple.id.eq(Q_DESIGN_COUPLE.id))
                .leftJoin(Q_DESIGN)
                    .on(Q_DESIGN.id.eq(Q_DESIGN_COUPLE_DESIGN.design.id))
                .leftJoin(Q_DESIGN_METAL_SPECIFICATION)
                    .on(Q_DESIGN_COUPLE_DESIGN.design.id.eq(Q_DESIGN_METAL_SPECIFICATION.design.id))
                .leftJoin(Q_DESIGN_COLLECTION)
                    .on(Q_DESIGN_COLLECTION.id.eq(Q_DESIGN.designCollection.id));

        query.where(Q_DESIGN_METAL_SPECIFICATION.metalSpecification.id.eq(viewCoupleDesignInput.getMetalSpecificationId())
                .and(Q_DESIGN_COLLECTION.id.eq(viewCoupleDesignInput.getCollectionId()))
                .and((Q_DESIGN.metalWeight.weight
                        .multiply(getMinMetalPriceQuery).multiply(3.75)
                                .add(getMinDiamondPriceQuery.
                                        multiply(BigDecimal.valueOf(1.3)))).
                        between(viewCoupleDesignInput.getMinPrice(), viewCoupleDesignInput.getMaxPrice())));
        return query;
    }
}
