package com.cplerings.core.application.metal.datasource;

import com.cplerings.core.application.metal.datasource.result.MetalSpecifications;
import com.cplerings.core.application.metal.input.ViewMetalSpecificationInput;

public interface ViewMetalSpecificationDataSource {

    MetalSpecifications getMetalSpecifications(ViewMetalSpecificationInput input);
}
