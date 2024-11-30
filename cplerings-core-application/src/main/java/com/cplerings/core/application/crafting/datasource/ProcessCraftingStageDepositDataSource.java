package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageHistory;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.spouse.Agreement;

import java.util.Optional;

public interface ProcessCraftingStageDepositDataSource {

    Optional<CraftingStage> findById(Long craftingStageId);

    CraftingStage save(CraftingStage craftingStage);

    CustomOrder save(CustomOrder customOrder);

    TransportationOrder save(TransportationOrder transportationOrder);

    Agreement save(Agreement agreement);

    CustomOrderHistory save(CustomOrderHistory customOrderHistory);

    TransportOrderHistory save(TransportOrderHistory transportOrderHistory);

    CraftingStageHistory save(CraftingStageHistory craftingStageHistory);
}
