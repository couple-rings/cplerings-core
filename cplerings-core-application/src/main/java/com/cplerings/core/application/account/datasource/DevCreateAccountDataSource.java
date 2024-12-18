package com.cplerings.core.application.account.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.branch.Branch;

public interface DevCreateAccountDataSource {

    Branch getBranchReferenceById(Long branchId);

    Account save(Account account);
}
