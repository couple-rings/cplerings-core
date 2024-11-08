package com.cplerings.core.infrastructure.service.price;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.cplerings.core.application.shared.service.price.CalculationTotalPrice;
import com.cplerings.core.domain.shared.valueobject.Money;

@Service
public class CalculationTotalPriceImpl implements CalculationTotalPrice {

    @Override
    public BigDecimal calculationTotalPrice(Money firstMetalPrice, Money secondMetalPrice, Money firstDiamondSpecPrice, Money secondDiamondSpecPrice, BigDecimal firstMetalWeight, BigDecimal secondMetalWeight, int firstSideDiamondCount, int secondSideDiamondCount, double sideDiamondPrice) {
        BigDecimal firstRingPrice = (firstMetalPrice
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(firstMetalWeight)
                .add(firstDiamondSpecPrice.getAmount())
                .add(BigDecimal.valueOf(firstSideDiamondCount)
                        .multiply(BigDecimal.valueOf(sideDiamondPrice))))
                .multiply(BigDecimal.valueOf(1.3));
        BigDecimal secondRingPrice = (secondMetalPrice
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(secondMetalWeight)
                .add(secondDiamondSpecPrice.getAmount())
                .add(BigDecimal.valueOf(secondSideDiamondCount)
                        .multiply(BigDecimal.valueOf(sideDiamondPrice))))
                .multiply(BigDecimal.valueOf(1.3));
        return firstRingPrice.add(secondRingPrice);
    }
}
