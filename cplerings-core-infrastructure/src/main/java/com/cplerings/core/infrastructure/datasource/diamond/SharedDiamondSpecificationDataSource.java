package com.cplerings.core.infrastructure.datasource.diamond;

import com.cplerings.core.application.diamond.datasource.ViewDiamondSpecificationDataSource;
import com.cplerings.core.application.diamond.datasource.result.DiamondSpecifications;
import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.diamond.QDiamondSpecification;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;

import java.util.List;

@DataSource
public class SharedDiamondSpecificationDataSource extends AbstractDataSource implements ViewDiamondSpecificationDataSource {

    private static final QDiamondSpecification Q_DIAMOND_SPECIFICATION = QDiamondSpecification.diamondSpecification;

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
}
