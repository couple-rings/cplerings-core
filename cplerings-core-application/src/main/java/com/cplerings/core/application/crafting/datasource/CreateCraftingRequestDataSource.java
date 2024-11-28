package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestHistory;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;

import java.util.Optional;

public interface CreateCraftingRequestDataSource {

    Optional<Account> getAccountByCustomerId(Long customerId);

    Optional<DiamondSpecification> getDiamondSpecificationByDiamondSpecId(Long diamondSpecId);

    Optional<MetalSpecification> getMetalSpecificationByMetalSpecId(Long metalSpecId);

    Optional<CustomDesign> getCustomDesignByCustomDesignId(Long customDesignId);

    CraftingRequest save(CraftingRequest craftingRequest);

    Optional<Branch> getBranchById(Long branchId);

    CraftingRequestHistory save(CraftingRequestHistory craftingRequestHistory);
}
