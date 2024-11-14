package com.cplerings.core.api.spouse.data;

import java.util.List;

import com.cplerings.core.application.shared.entity.spouse.ASpouse;

import lombok.Builder;

@Builder
public record SpouseList(List<ASpouse> spouses) {
}
