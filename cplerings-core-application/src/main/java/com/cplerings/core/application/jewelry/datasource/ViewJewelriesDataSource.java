package com.cplerings.core.application.jewelry.datasource;

import com.cplerings.core.application.jewelry.datasource.result.Jewelries;
import com.cplerings.core.application.jewelry.input.ViewJewelriesInput;

public interface ViewJewelriesDataSource {

    Jewelries getJewelries(ViewJewelriesInput input);
}
