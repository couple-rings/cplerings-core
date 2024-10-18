package com.cplerings.core.api.spouse.request;

import lombok.Builder;

import jakarta.validation.constraints.NotNull;

@Builder
public record CreateSpouseRequest(@NotNull PrimarySpouse primarySpouse, @NotNull SecondarySpouse secondarySpouse) {

}
