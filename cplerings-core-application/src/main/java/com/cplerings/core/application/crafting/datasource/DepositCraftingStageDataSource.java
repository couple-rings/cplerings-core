package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.payment.PaymentReceiver;

import java.util.Optional;

public interface DepositCraftingStageDataSource {

    Optional<CraftingStage> findCraftingStageById(Long craftingStageId);

    PaymentReceiver save(PaymentReceiver paymentReceiver);
}
