package com.cplerings.core.application.shared.service.price;

import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CraftingStageAmounts {

    private Money firstCraftingStageAmount;
    private Money secondCraftingStageAmount;
    private Money thirdCraftingStageAmount;
}
