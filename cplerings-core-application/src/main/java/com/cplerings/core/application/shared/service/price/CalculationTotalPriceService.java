package com.cplerings.core.application.shared.service.price;

import com.cplerings.core.domain.shared.valueobject.Money;

import java.math.BigDecimal;

public interface CalculationTotalPriceService {

    Money calculationTotalPrice(Money metalPrice, Money diamondSpecPrice, BigDecimal metalWeight, int sideDiamondCount, double sideDiamondPrice);
}
