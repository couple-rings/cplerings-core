package com.cplerings.core.api.spouse.request;

import jakarta.validation.constraints.NotNull;

public record CreateSpouseRequest(@NotNull PrimarySpouse primarySpouse, @NotNull SecondarySpouse secondarySpouse) {
}
