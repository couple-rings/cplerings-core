package com.cplerings.core.application.agreement.datasource;

import com.cplerings.core.application.agreement.datasource.result.Agreements;
import com.cplerings.core.application.agreement.input.ViewAgreementsInput;

public interface ViewAgreementsDataSource {

    Agreements getAgreements(ViewAgreementsInput input);
}
