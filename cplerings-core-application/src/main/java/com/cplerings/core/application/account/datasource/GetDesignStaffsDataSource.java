package com.cplerings.core.application.account.datasource;

import com.cplerings.core.application.account.datasource.result.DesignStaffsResult;
import com.cplerings.core.application.account.input.GetDesignStaffsInput;
import com.cplerings.core.application.shared.entity.account.ADesignStaff;

public interface GetDesignStaffsDataSource {

    DesignStaffsResult getDesignStaffs(GetDesignStaffsInput input);

    Long calculateNoOfHandleCustomRequest(ADesignStaff staff);
}
