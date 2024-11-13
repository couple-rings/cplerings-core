package com.cplerings.core.application.account.datasource;

import com.cplerings.core.application.account.datasource.result.Transporters;
import com.cplerings.core.application.account.input.ViewTransportersInput;

public interface ViewTransportersDataSource {

    Transporters getTransporters(ViewTransportersInput input);
}
