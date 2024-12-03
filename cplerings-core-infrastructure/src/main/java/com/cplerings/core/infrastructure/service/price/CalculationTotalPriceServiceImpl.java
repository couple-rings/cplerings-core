package com.cplerings.core.infrastructure.service.price;

import com.cplerings.core.application.shared.service.price.CalculationTotalPriceService;
import com.cplerings.core.domain.shared.valueobject.Money;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculationTotalPriceServiceImpl implements CalculationTotalPriceService {

    @Override
    public Money calculationTotalPrice(Money metalPrice, Money diamondSpecPrice, BigDecimal metalWeight, int sideDiamondCount, double sideDiamondPrice) {
        BigDecimal ringPrice = (metalPrice
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(metalWeight)
                .add(diamondSpecPrice.getAmount())
                .add(BigDecimal.valueOf(sideDiamondCount)
                        .multiply(BigDecimal.valueOf(sideDiamondPrice))))
                .multiply(BigDecimal.valueOf(1.3));
        return Money.create(ringPrice);
    }

    @Override
    public Money calculationPriceForJewelry(Money metalPrice, BigDecimal metalWeight, int sideDiamondCount, BigDecimal sideDiamondPrice) {
        BigDecimal ringPrice = (metalPrice
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(metalWeight)
                .add(BigDecimal.valueOf(sideDiamondCount)
                        .multiply(sideDiamondPrice)))
                .multiply(BigDecimal.valueOf(1.3));
        return Money.create(ringPrice);
    }
}
