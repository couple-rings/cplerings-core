package com.cplerings.core.application.jewelry.datasource;

import java.util.Optional;

import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.metal.MetalSpecification;

public interface UpdateJewelryDataSource {

    Optional<Jewelry> getJewelryById(Long id);

    Jewelry save(Jewelry jewelry);

    Optional<Design> getDesignById(Long id);

    Optional<MetalSpecification> getMetalSpecificationById(Long id);
}
