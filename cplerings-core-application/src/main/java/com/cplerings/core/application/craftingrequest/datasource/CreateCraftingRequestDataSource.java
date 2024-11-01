package com.cplerings.core.application.craftingrequest.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;

public interface CreateCraftingRequestDataSource {

    Optional<Account> getAccountByCustomerId(Long customerId);
    Optional<DiamondSpecification> getDiamondSpecificationByDiamondSpecId(Long diamondSpecId);
    Optional<MetalSpecification> getMetalSpecificationByMetalSpecId(Long metalSpecId);
    Optional<CustomDesign> getCustomDesignByCustomDesignId(Long customDesignId);
    CraftingRequest save(CraftingRequest craftingRequest);
}
