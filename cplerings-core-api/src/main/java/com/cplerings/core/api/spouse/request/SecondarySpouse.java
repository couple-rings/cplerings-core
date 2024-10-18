package com.cplerings.core.api.spouse.request;

import lombok.Builder;

import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

@Builder
public record SecondarySpouse(@NotBlank String citizenId, @NotBlank Instant dateOfBirth, @NotBlank String fullName) {

}
