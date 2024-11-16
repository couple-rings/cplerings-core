package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.application.crafting.datasource.result.CraftingStages;
import com.cplerings.core.application.crafting.input.ViewCraftingStagesInput;

public interface ViewCraftingStagesDataSource {

    CraftingStages getCraftingStages(ViewCraftingStagesInput input);
}
