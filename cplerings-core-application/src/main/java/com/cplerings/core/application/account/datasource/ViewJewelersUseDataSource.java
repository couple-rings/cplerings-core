package com.cplerings.core.application.account.datasource;

import com.cplerings.core.application.account.datasource.result.Jewelers;
import com.cplerings.core.application.account.input.ViewJewelersInput;

public interface ViewJewelersUseDataSource {

    Jewelers getJewelers(ViewJewelersInput input);
}
