package com.cplerings.core.application.branch.datasource;

import com.cplerings.core.application.branch.datasource.result.Branches;
import com.cplerings.core.application.branch.input.ViewBranchesInput;

public interface ViewBranchesDataSource {

    Branches getBranches(ViewBranchesInput input);
}
