package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.payment.PaymentReceiver;

import java.util.Optional;

public interface DepositCraftingStageDataSource {

    Optional<CraftingStage> findCraftingStageById(Long craftingStageId);

    PaymentReceiver save(PaymentReceiver paymentReceiver);

    Optional<TransportationAddress> findTransportAddressById(Long transportAddressId);

    CustomOrder save(CustomOrder customOrder);
}
