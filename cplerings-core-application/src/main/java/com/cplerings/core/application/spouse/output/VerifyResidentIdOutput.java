package com.cplerings.core.application.spouse.output;

import com.cplerings.core.application.shared.entity.spouse.ASpouse;

import lombok.Builder;

@Builder
public record VerifyResidentIdOutput(ASpouse spouse) {
}
