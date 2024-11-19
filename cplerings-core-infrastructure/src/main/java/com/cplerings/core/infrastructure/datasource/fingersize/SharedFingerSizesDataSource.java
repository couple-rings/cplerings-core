package com.cplerings.core.infrastructure.datasource.fingersize;

import java.util.List;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.fingersize.datasource.ViewFingerSizesDataSource;
import com.cplerings.core.application.fingersize.datasource.result.FingerSizes;
import com.cplerings.core.application.fingersize.input.ViewFingerSizesInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.ring.FingerSize;
import com.cplerings.core.domain.ring.QFingerSize;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

@DataSource
public class SharedFingerSizesDataSource extends AbstractDataSource implements ViewFingerSizesDataSource {

    private static final QFingerSize Q_FINGER_SIZE = QFingerSize.fingerSize;

    @Override
    public FingerSizes getFingerSizes(ViewFingerSizesInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<FingerSize> query = createQuery()
                .select(Q_FINGER_SIZE)
                .from(Q_FINGER_SIZE);
        long count = query.distinct().fetchCount();
        List<FingerSize> fingerSizes = query.limit(input.getPageSize()).offset(offset).fetch();
        return FingerSizes.builder()
                .fingerSizes(fingerSizes)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
