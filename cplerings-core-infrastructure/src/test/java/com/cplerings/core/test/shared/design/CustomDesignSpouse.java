package com.cplerings.core.test.shared.design;

import java.util.List;

import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.spouse.Spouse;

import lombok.Builder;

@Builder
public record CustomDesignSpouse(List<CustomDesign> customDesign, Spouse[] spouses) {
}
