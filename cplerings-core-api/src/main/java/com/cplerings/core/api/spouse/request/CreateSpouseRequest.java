package com.cplerings.core.api.spouse.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateSpouseRequest(@NotNull PrimarySpouse primarySpouse, @NotNull SecondarySpouse secondarySpouse) {
}
