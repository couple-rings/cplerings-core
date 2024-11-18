package com.cplerings.core.infrastructure.datasource.agreement;

import java.util.List;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.agreement.datasource.ViewAgreementsDataSource;
import com.cplerings.core.application.agreement.datasource.result.Agreements;
import com.cplerings.core.application.agreement.input.ViewAgreementsInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.spouse.Agreement;
import com.cplerings.core.domain.spouse.QAgreement;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

@DataSource
public class SharedAgreementDataSource extends AbstractDataSource implements ViewAgreementsDataSource {

    private final static QAgreement Q_AGREEMENT = QAgreement.agreement;

    @Override
    public Agreements getAgreements(ViewAgreementsInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Agreement> query = createQuery()
                .select(Q_AGREEMENT)
                .from(Q_AGREEMENT);
        long count = query.distinct().fetchCount();
        List<Agreement> agreements = query.limit(input.getPageSize()).offset(offset).fetch();
        return Agreements.builder()
                .agreements(agreements)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
