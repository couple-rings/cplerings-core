package com.cplerings.core.application.spouse.datasource.result;

import java.util.List;

import com.cplerings.core.domain.spouse.Spouse;

import lombok.Builder;

@Builder
public record SpouseList(List<Spouse> spouses) {
}
