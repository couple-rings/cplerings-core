package com.cplerings.core.application.crafting.datasource;

import java.util.Optional;

import com.cplerings.core.domain.design.crafting.CraftingRequest;

public interface ViewCraftingRequestDataSource {

    Optional<CraftingRequest> getCraftingRequest(Long craftingRequestId);
}
