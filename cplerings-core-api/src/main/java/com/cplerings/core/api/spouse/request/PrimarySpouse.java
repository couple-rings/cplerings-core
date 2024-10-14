package com.cplerings.core.api.spouse.request;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PrimarySpouse (@NotBlank String citizenId, @NotBlank Instant dateOfBirth, @NotBlank String fullName, @NotBlank Long customerId) {

}
