package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.application.crafting.datasource.result.CraftingRequestGroupsList;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsGroupsInput;

public interface ViewCraftingRequestsGroupsDataSource {

    CraftingRequestGroupsList getAccountCraftingRequests(ViewCraftingRequestsGroupsInput input);
}
