package com.cplerings.core.application.crafting.datasource;

import java.util.Optional;

import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.order.CustomOrder;

public interface DepositCraftingStageDataSource {

    Optional<CraftingStage> findCraftingStageById(Long craftingStageId);

    Optional<TransportationAddress> findTransportAddressById(Long transportAddressId);

    CustomOrder save(CustomOrder customOrder);
}
