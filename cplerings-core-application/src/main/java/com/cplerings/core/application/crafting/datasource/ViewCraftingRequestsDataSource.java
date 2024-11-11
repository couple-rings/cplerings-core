package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.application.crafting.datasource.result.CraftingRequests;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsInput;

public interface ViewCraftingRequestsDataSource {

    CraftingRequests getCraftingrequests(ViewCraftingRequestsInput input);
}
