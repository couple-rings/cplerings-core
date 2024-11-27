package com.cplerings.core.application.crafting.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.spouse.Spouse;

public interface CraftingRingDataSource {

    Optional<Account> getCustomerById(Long id);

    Optional<Branch> getBranchById(Long id);

    Optional<Spouse> getSpouseById(Long id);

    Optional<Design> getDesignByDesignId(Long id);

    Optional<MetalSpecification> getMetalSpecificationById(Long id);

    Optional<DiamondSpecification> getDiamondSpecificationById(Long id);

    DesignVersion save(DesignVersion designVersion);

    CustomDesign saveCustomDesign(CustomDesign customDesign);

    CraftingRequest save(CraftingRequest craftingRequest);

    void updateDesignUnavailable(Design design);
}
