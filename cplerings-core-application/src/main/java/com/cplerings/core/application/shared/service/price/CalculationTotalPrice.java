package com.cplerings.core.application.shared.service.price;

import java.math.BigDecimal;

import com.cplerings.core.domain.shared.valueobject.Money;

public interface CalculationTotalPrice {

    BigDecimal calculationTotalPrice(Money firstMetalPrice, Money secondMetalPrice, Money firstDiamondSpecPrice, Money secondDiamondSpecPrice, BigDecimal firstMetalWight, BigDecimal secondMetalWeight, int firstSideDiamondCount, int secondSideDiamondCount, double sideDiamondPrice);
}
