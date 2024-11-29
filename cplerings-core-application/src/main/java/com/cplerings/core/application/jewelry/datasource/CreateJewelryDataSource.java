package com.cplerings.core.application.jewelry.datasource;

import java.util.Optional;

import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.metal.MetalSpecification;

public interface CreateJewelryDataSource {

    Optional<Branch> getBranchById(Long id);

    Optional<Design> getDesignById(Long id);

    Optional<Diamond> getDiamondById(Long id);

    Optional<MetalSpecification> getMetalSpecById(Long id);

    Jewelry save(Jewelry jewelry);
}
