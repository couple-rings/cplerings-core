package com.cplerings.core.infrastructure.service.price;

import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.price.CalculationService;
import com.cplerings.core.application.shared.service.price.CraftingStageAmounts;
import com.cplerings.core.application.shared.service.price.CraftingStageInfo;
import com.cplerings.core.application.shared.service.price.CustomOrderInfo;
import com.cplerings.core.application.shared.service.price.RingInfo;
import com.cplerings.core.domain.order.Difficulty;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final ConfigurationService configurationService;

    @Override
    public Money calculationTotalPrice(Money metalPrice, Money diamondSpecPrice, BigDecimal metalWeight, int sideDiamondCount, double sideDiamondPrice) {
        Double priceApplicationRatio = configurationService.getPriceApplicationRatio();
        Money shippingFee = configurationService.getShippingFee();
        Money craftingFee = configurationService.getCraftingFee();

        BigDecimal ringPrice = (metalPrice
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(metalWeight)
                .add(diamondSpecPrice.getAmount())
                .add(BigDecimal.valueOf(sideDiamondCount)
                        .multiply(BigDecimal.valueOf(sideDiamondPrice)))
                .add(craftingFee.getAmount())
                .add(shippingFee.getAmount()))
                .multiply(BigDecimal.valueOf(priceApplicationRatio));
        return Money.create(ringPrice);
    }

    @Override
    public Money calculationPriceForJewelry(Money metalPrice, BigDecimal metalWeight, int sideDiamondCount, BigDecimal sideDiamondPrice) {
        Double priceApplicationRatio = configurationService.getPriceApplicationRatio();
        Money shippingFee = configurationService.getShippingFee();

        BigDecimal ringPrice = (metalPrice
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(metalWeight)
                .add(BigDecimal.valueOf(sideDiamondCount)
                        .multiply(sideDiamondPrice))
                .add(shippingFee.getAmount()))
                .multiply(BigDecimal.valueOf(priceApplicationRatio));
        return Money.create(ringPrice);
    }

    @Override
    public Money calculateRingPrice(RingInfo ringInfo) {
        return ringInfo.getMetalPricePerUnit()
                .multiply(ringInfo.getMetalWeight().getWeightValue())
                .multiply(configurationService.getMetalWeightRatio())
                .add(ringInfo.getDiamondPrice())
                .add(ringInfo.getSideDiamondPrice()
                        .multiply(BigDecimal.valueOf(ringInfo.getSideDiamondCount())))
                .add(ringInfo.getCraftingFee())
                .multiply(BigDecimal.valueOf(configurationService.getPriceApplicationRatio()));
    }

    @Override
    public Money calculateCraftingFee(Difficulty difficulty) {
        return switch (difficulty) {
            case NORMAL -> configurationService.getCraftingFee();
            case HARD ->
                    configurationService.getCraftingFee().multiply(configurationService.getCraftingFeeHardMultiplier());
        };
    }

    @Override
    public Money calculateTotalPrice(CustomOrderInfo customOrderInfo) {
        Money customOrderTotalPrice = Money.create(BigDecimal.ZERO);

        for (Money ringPrice : customOrderInfo.getRingPrices()) {
            customOrderTotalPrice = customOrderTotalPrice.add(ringPrice);
        }

        return customOrderTotalPrice.add(configurationService.getShippingFee());
    }

    @Override
    public CraftingStageAmounts calculateCraftingStageAmounts(CraftingStageInfo craftingStageInfo) {
        final BigDecimal firstStagePercentage = BigDecimal.valueOf(configurationService.getCraftingStageProgress1())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
        final Money firstStageAmount = craftingStageInfo.getTotalPrice()
                .multiply(firstStagePercentage);

        final BigDecimal secondStagePercentage = BigDecimal.valueOf(configurationService.getCraftingStageProgress2())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN)
                .subtract(firstStagePercentage);
        final Money secondStageAmount = craftingStageInfo.getTotalPrice()
                .multiply(secondStagePercentage);

        final Money thirdStageAmount = craftingStageInfo.getTotalPrice()
                .subtract(firstStageAmount)
                .subtract(secondStageAmount);

        return CraftingStageAmounts.builder()
                .firstCraftingStageAmount(firstStageAmount)
                .secondCraftingStageAmount(secondStageAmount)
                .thirdCraftingStageAmount(thirdStageAmount)
                .build();
    }
}
