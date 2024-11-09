package com.cplerings.core.application.diamond.datasource;

import com.cplerings.core.application.diamond.datasource.result.DiamondSpecifications;
import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;

public interface ViewDiamondSpecificationDataSource {

    DiamondSpecifications getDiamondSpecifications(ViewDiamondSpecificationInput input);
}
