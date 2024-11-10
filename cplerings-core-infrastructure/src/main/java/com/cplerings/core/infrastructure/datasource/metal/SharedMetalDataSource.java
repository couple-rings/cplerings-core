package com.cplerings.core.infrastructure.datasource.metal;

import java.util.List;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.metal.datasource.ViewMetalSpecificationDataSource;
import com.cplerings.core.application.metal.datasource.result.MetalSpecifications;
import com.cplerings.core.application.metal.input.ViewMetalSpecificationInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.metal.QMetalSpecification;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

@DataSource
public class SharedMetalDataSource extends AbstractDataSource implements ViewMetalSpecificationDataSource {

    private static final QMetalSpecification Q_METAL_SPECIFICATION = QMetalSpecification.metalSpecification;

    @Override
    public MetalSpecifications getMetalSpecifications(ViewMetalSpecificationInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<MetalSpecification> query = createQuery()
                .select(Q_METAL_SPECIFICATION)
                .from(Q_METAL_SPECIFICATION);
        long count = query.distinct().fetchCount();
        List<MetalSpecification> metalSpecifications = query.limit(input.getPageSize()).offset(offset).fetch();
        return MetalSpecifications.builder()
                .metalSpecifications(metalSpecifications)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
