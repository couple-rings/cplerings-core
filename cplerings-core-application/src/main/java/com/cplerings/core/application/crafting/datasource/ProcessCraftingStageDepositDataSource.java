package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.TransportationOrder;

import java.util.Optional;

public interface ProcessCraftingStageDepositDataSource {

    Optional<CraftingStage> findById(Long craftingStageId);

    CraftingStage save(CraftingStage craftingStage);

    CustomOrder save(CustomOrder customOrder);

    TransportationOrder save(TransportationOrder transportationOrder);
}
