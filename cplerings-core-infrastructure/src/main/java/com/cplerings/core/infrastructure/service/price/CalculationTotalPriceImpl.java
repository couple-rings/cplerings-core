package com.cplerings.core.infrastructure.service.price;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.cplerings.core.application.shared.service.price.CalculationTotalPrice;
import com.cplerings.core.domain.design.crafting.CraftingRequest;

@Service
public class CalculationTotalPriceImpl implements CalculationTotalPrice {

    @Override
    public BigDecimal calculationTotalPrice(CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest, Double sideDiamondPrice) {
        BigDecimal firstRingPrice = (firstCraftingRequest.getMetalSpecification().getPricePerUnit()
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(firstCraftingRequest.getCustomDesign().getMetalWeight().getWeightValue())
                .add(firstCraftingRequest.getDiamondSpecification().getPrice().getAmount())
                .add(BigDecimal.valueOf(firstCraftingRequest.getCustomDesign().getSideDiamondsCount())
                        .multiply(BigDecimal.valueOf(sideDiamondPrice))))
                .multiply(BigDecimal.valueOf(1.3));
        BigDecimal secondRingPrice = (secondCraftingRequest.getMetalSpecification().getPricePerUnit()
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(secondCraftingRequest.getCustomDesign().getMetalWeight().getWeightValue())
                .add(secondCraftingRequest.getDiamondSpecification().getPrice().getAmount())
                .add(BigDecimal.valueOf(secondCraftingRequest.getCustomDesign().getSideDiamondsCount())
                        .multiply(BigDecimal.valueOf(sideDiamondPrice))))
                .multiply(BigDecimal.valueOf(1.3));
        return firstRingPrice.add(secondRingPrice);
    }
}
