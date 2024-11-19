package com.cplerings.core.infrastructure.datasource.branch;

import java.util.List;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.cplerings.core.application.branch.datasource.ViewBranchesDataSource;
import com.cplerings.core.application.branch.datasource.result.Branches;
import com.cplerings.core.application.branch.input.ViewBranchesInput;
import com.cplerings.core.common.pagination.PaginationUtils;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.branch.QBranch;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

@DataSource
public class SharedBranchDataSource extends AbstractDataSource implements ViewBranchesDataSource {

    private static final QBranch Q_BRANCH = QBranch.branch;

    @Override
    public Branches getBranches(ViewBranchesInput input) {
        var offset = PaginationUtils.getOffset(input.getPage(), input.getPageSize());
        BlazeJPAQuery<Branch> query = createQuery()
                .select(Q_BRANCH)
                .from(Q_BRANCH);
        long count = query.distinct().fetchCount();
        List<Branch> branches = query.limit(input.getPageSize()).offset(offset).fetch();
        return Branches.builder()
                .branches(branches)
                .count(count)
                .page(input.getPage())
                .pageSize(input.getPageSize())
                .build();
    }
}
