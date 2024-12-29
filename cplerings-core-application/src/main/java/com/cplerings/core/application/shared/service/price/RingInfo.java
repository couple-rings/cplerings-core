package com.cplerings.core.application.shared.service.price;

import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.shared.valueobject.Weight;

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
public class RingInfo {

    private Money metalPricePerUnit;
    private Weight metalWeight;
    private Money diamondPrice;
    private Money sideDiamondPrice;
    private int sideDiamondCount;
    private Money craftingFee;
}
