package com.cplerings.core.application.shared.service.price;

import com.cplerings.core.domain.order.Difficulty;
import com.cplerings.core.domain.shared.valueobject.Money;

import java.math.BigDecimal;

public interface CalculationService {

    Money calculationTotalPrice(Money metalPrice, Money diamondSpecPrice, BigDecimal metalWeight, int sideDiamondCount, double sideDiamondPrice);

    Money calculationPriceForJewelry(Money metalPrice, BigDecimal metalWeight, int sideDiamondCount, BigDecimal sideDiamondPrice);

    Money calculateRingPrice(RingInfo ringInfo);

    Money calculateCraftingFee(Difficulty difficulty);

    Money calculateTotalPrice(CustomOrderInfo customOrderInfo);

    CraftingStageAmounts calculateCraftingStageAmounts(CraftingStageInfo craftingStageInfo);
}
