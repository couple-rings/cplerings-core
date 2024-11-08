package com.cplerings.core.application.shared.service.price;

import java.math.BigDecimal;

import com.cplerings.core.domain.design.crafting.CraftingRequest;

public interface CalculationTotalPrice {

    BigDecimal calculationTotalPrice(CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest, Double sideDiamondPrice);
}
