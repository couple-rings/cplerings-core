package com.cplerings.core.application.shared.service.price;

import java.math.BigDecimal;

import com.cplerings.core.domain.shared.valueobject.Money;

public interface CalculationTotalPrice {

    Money calculationTotalPrice(Money metalPrice, Money diamondSpecPrice, BigDecimal metalWeight, int sideDiamondCount, double sideDiamondPrice);
}
