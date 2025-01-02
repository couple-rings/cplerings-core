package com.cplerings.core.test.component.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.application.shared.service.price.CalculationService;
import com.cplerings.core.application.shared.service.price.CraftingStageAmounts;
import com.cplerings.core.application.shared.service.price.CraftingStageInfo;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.test.shared.AbstractCT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

class CalculationServiceCT extends AbstractCT {

    @Autowired
    private CalculationService calculationService;

    @Test
    void givenCalculationService_whenCalculateCraftingStageAmounts() {
        final CraftingStageInfo craftingStageInfo = CraftingStageInfo.builder()
                .totalPrice(Money.create(BigDecimal.valueOf(100000)))
                .build();

        final CraftingStageAmounts result = calculationService.calculateCraftingStageAmounts(craftingStageInfo);

        thenCraftingStageAmountsAreCorrect(result);
    }

    private void thenCraftingStageAmountsAreCorrect(CraftingStageAmounts result) {
        assertThat(result).isNotNull();

        assertThat(result.getFirstCraftingStageAmount().getAmount())
                .isEqualByComparingTo(BigDecimal.valueOf(50000));

        assertThat(result.getSecondCraftingStageAmount().getAmount())
                .isEqualByComparingTo(BigDecimal.valueOf(25000));

        assertThat(result.getThirdCraftingStageAmount().getAmount())
                .isEqualByComparingTo(BigDecimal.valueOf(25000));
    }
}
