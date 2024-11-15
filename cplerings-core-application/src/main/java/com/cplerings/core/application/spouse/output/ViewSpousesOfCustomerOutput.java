package com.cplerings.core.application.spouse.output;

import java.util.List;

import com.cplerings.core.application.shared.entity.spouse.ASpouse;

import lombok.Builder;

@Builder
public record ViewSpousesOfCustomerOutput(List<ASpouse> spouses) {
}
