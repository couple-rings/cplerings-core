package com.cplerings.core.application.spouse.output;

import java.util.List;

import com.cplerings.core.domain.spouse.Spouse;

import lombok.Builder;

@Builder
public record ViewSpousesOfCustomerOutput(List<Spouse> spouses) {
}