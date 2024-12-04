package com.cplerings.core.infrastructure.service.price;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.price.CalculationTotalPriceService;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculationTotalPriceServiceImpl implements CalculationTotalPriceService {

    private final ConfigurationService configurationService;

    @Override
    public Money calculationTotalPrice(Money metalPrice, Money diamondSpecPrice, BigDecimal metalWeight, int sideDiamondCount, double sideDiamondPrice) {
        Double priceApplicationRatio = configurationService.getPriceApplicationRatio();

        BigDecimal ringPrice = (metalPrice
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(metalWeight)
                .add(diamondSpecPrice.getAmount())
                .add(BigDecimal.valueOf(sideDiamondCount)
                        .multiply(BigDecimal.valueOf(sideDiamondPrice))))
                .multiply(BigDecimal.valueOf(priceApplicationRatio));
        return Money.create(ringPrice);
    }

    @Override
    public Money calculationPriceForJewelry(Money metalPrice, BigDecimal metalWeight, int sideDiamondCount, BigDecimal sideDiamondPrice) {
        Double priceApplicationRatio = configurationService.getPriceApplicationRatio();

        BigDecimal ringPrice = (metalPrice
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(metalWeight)
                .add(BigDecimal.valueOf(sideDiamondCount)
                        .multiply(sideDiamondPrice)))
                .multiply(BigDecimal.valueOf(priceApplicationRatio));
        return Money.create(ringPrice);
    }
}
