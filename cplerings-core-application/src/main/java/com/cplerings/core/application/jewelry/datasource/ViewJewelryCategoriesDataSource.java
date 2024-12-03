package com.cplerings.core.application.jewelry.datasource;

import com.cplerings.core.application.jewelry.datasource.result.JewelryCategories;
import com.cplerings.core.application.jewelry.input.ViewJewelryCategoriesInput;

public interface ViewJewelryCategoriesDataSource {

    JewelryCategories getJewelryCategories(ViewJewelryCategoriesInput input);
}
